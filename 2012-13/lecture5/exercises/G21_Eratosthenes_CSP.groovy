import static groovyx.gpars.dataflow.Dataflow.task as go

import groovyx.gpars.dataflow.DataflowQueue

/**
 * Demonstrates concurrent implementation of the Sieve of Eratosthenes using dataflow tasks mimicking closely the example
 * given in the Google's Go programming language.
 *
 * In principle, the algorithm consists of concurrently run chained filters,
 * each of which detects whether the current number can be divided by a single prime number.
 * (generate nums 1, 2, 3, 4, 5, ...) -> (filter by mod 2) -> (filter by mod 3) -> (filter by mod 5) -> (filter by mod 7) -> (filter by mod 11) -> (caution! Primes falling out here)
 * The chain is built (grows) on the fly, whenever a new prime is found
 */

// Send the sequence 2, 3, 4, ... to channel 'ch'.

def generate(ch) {
    {->
        for (i in (2..10000)) {
            ch << i
        }
    }
}

// Copy the values from channel 'in' to channel 'out',
// removing those divisible by 'prime'.

def filter(inChannel, outChannel, int prime) {
    {->
        for (; ;) {
            def number = inChannel.val
            if (number % prime != 0) {
                outChannel << number
            }
        }
    }
}

// The prime sieve: Daisy-chain Filter processes.

def main() {
    final DataflowQueue ch = new DataflowQueue()
    go generate(ch)
    for (i in (1..10)) {
        int prime = ch.val
        println prime
        def ch1 = new DataflowQueue()
        go filter(ch, ch1, prime)
        ch = ch1
    }
}

//Now run it all
main()

