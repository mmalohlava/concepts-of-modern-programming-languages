import groovyx.gpars.dataflow.Promise
import static groovyx.gpars.GParsPool.withPool

withPool(1) {
    def sum = { a, b -> a + b }.asyncFun()
    def fib
    fib = { n ->
        n <= 2 ? 1 : sum(fib(n - 2), fib(n - 1))
    }.asyncFun()

    println "Starting the calculation"
    final Promise<Integer> result = fib(30)
    println "Now the calculation is running while we can do something else."

    sleep 1000
    println "Are we done yet? ${result.bound}"
    if (!result.bound) println "Let's do something else then, since the calculation is still running"

    sleep 1000
    println "Now really, are we done yet? ${result.bound}"

    println "OK, I've run out of patience. I'll sit down here and wait for you to finish my calculation!"
    println result.get()
}