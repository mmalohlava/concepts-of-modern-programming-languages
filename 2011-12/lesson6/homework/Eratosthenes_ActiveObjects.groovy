/**
 * Demonstrates concurrent implementation of the Sieve of Eratosthenes using active objects
 *
 * In principle, the algorithm consists of concurrently run chained filters,
 * each of which detects whether the current number can be divided by a single prime number.
 * (generate nums 1, 2, 3, 4, 5, ...) -> (filter by mod 2) -> (filter by mod 3) -> (filter by mod 5) -> (filter by mod 7) -> (filter by mod 11) -> (caution! Primes falling out here)
 * The chain is built (grows) on the fly, whenever a new prime is found
 */

int requestedPrimeNumberBoundary = 10000

final def firstFilter = new Filter(2)

/**
 * Generating candidate numbers and sending them to the chain of filters
 */
(2..requestedPrimeNumberBoundary).each {
    firstFilter.nextNumber(it)
}

sleep 5000

//TODO Implement the Filter class as an ActiveObject so that Filters can be chained to form a SieveOfEratosthenes
//and print all prime numbers from the range generated above
