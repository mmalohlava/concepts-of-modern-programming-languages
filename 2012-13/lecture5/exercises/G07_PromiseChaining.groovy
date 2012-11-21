import static groovyx.gpars.GParsPool.withPool
import static groovyx.gpars.dataflow.Dataflow.whenAllBound

/**
 * Uses the Dataflow.whenAllBound() and Promise.then() (aka rightShift '>>') methods to wire together multiple asynchronous functions.
 */

withPool {
    Closure download = { String url ->
        sleep 3000  //Simulate a web read
        'web content'
    }.asyncFun()

    Closure loadFile = { String fileName ->
        'file content'  //simulate a local file read
    }.asyncFun()

    Closure hash = { s -> s.hashCode() }

    Closure compare = { int first, int second ->
        first == second
    }

    def all = whenAllBound([
            download('http://www.gpars.org') >> hash,
            loadFile('/coolStuff/gpars/website/index.html') >> hash
    ], compare) >> { println "Comparison result: $it" }
    //TASK Block here until the whole calculation finishes
    println 'Finished'
}