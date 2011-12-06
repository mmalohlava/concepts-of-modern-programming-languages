import groovyx.gpars.dataflow.DataflowQueue
import static groovyx.gpars.dataflow.Dataflow.operator

final DataflowQueue result = new DataflowQueue()

final op = operator(...) {...}


30.times {
    println result.val
}
op.stop()