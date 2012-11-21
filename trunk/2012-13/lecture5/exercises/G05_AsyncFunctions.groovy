import static groovyx.gpars.GParsPool.withPool

/**
 * Asynchronous function composition
 */

withPool {
    Closure download = { String url ->
        sleep 3000
        'web content'
    }.asyncFun()

    Closure loadFile = { String fileName ->
        'file content'
    }.asyncFun()

    Closure hash = { s -> s.hashCode() }.asyncFun()

    Closure compare = { int first, int second ->
        first == second
    }.asyncFun()

    def result = compare(hash(download('http://www.gpars.org')), hash(loadFile('/coolStuff/gpars/website/index.html')))
    println "The result of comparison: " + result.get()
}
