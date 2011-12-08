import groovyx.gpars.dataflow.DataflowQueue
import static groovyx.gpars.dataflow.Dataflow.operator

final DataflowQueue result = new DataflowQueue()

//TODO define channels and an operator so that we get a sequence of Fibonacci numbers coming out of the "result" channel

final op = operator(...) {...}


30.times {
    println result.val
}
op.stop()