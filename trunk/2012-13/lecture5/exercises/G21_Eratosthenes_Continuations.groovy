import groovyx.gpars.dataflow.DataflowQueue
import groovyx.gpars.group.DefaultPGroup

/**
 * Demonstrates concurrent implementation of the Sieve of Eratosthenes using dataflow tasks with asynchronous value retrieval
 * Asynchronous value retrieval releases the current task's thread whenever waiting for a value to read from the DataflowQueue.
 *
 * In principle, the algorithm consists of concurrently run chained filters,
 * each of which detects whether the current number can be divided by a single prime number.
 * (generate nums 1, 2, 3, 4, 5, ...) -> (filter by mod 2) -> (filter by mod 3) -> (filter by mod 5) -> (filter by mod 7) -> (filter by mod 11) -> (caution! Primes falling out here)
 * The chain is built (grows) on the fly, whenever a new prime is found
 */

group = new DefaultPGroup()

final int requestedPrimeNumberCount = 1000

final DataflowQueue initialChannel = new DataflowQueue()

/**
 * Generating candidate numbers
 */
group.task {
    (2..10000).each {
        initialChannel << it
    }
}

/**
 * Chain a new filter for a particular prime number to the end of the Sieve
 * @param inChannel The current end channel to consume
 * @param prime The prime number to divide future prime candidates with
 * @return A new channel ending the whole chain
 */
def filter(inChannel, int prime) {
    def outChannel = new DataflowQueue()
    inChannel.whenBound {
        doFilter(it, prime, inChannel, outChannel)
    }
    return outChannel
}

def doFilter(number, prime, inChannel, outChannel) {
    if (number % prime != 0) {
        outChannel << number
    }
    inChannel.whenBound {
        doFilter(it, prime, inChannel, outChannel)
    }

}
/**
 * Consume Sieve output and add additional filters for all found primes
 */
group.task {
    def currentOutput = initialChannel
    requestedPrimeNumberCount.times {
        int prime = currentOutput.val
        println "Found: $prime"
        currentOutput = filter(currentOutput, prime)
    }
}.join()
