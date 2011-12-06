import groovyx.gpars.dataflow.DataflowVariable
import groovyx.gpars.group.NonDaemonPGroup
import groovyx.gpars.group.PGroup

def println = {synchronized (this) {System.out.println("Output: " + it);}}
final int SPEED_OF_LIGHT = 299792458L
final PGroup group = new NonDaemonPGroup()
final def inputReader = new InputStreamReader(System.in)

def mass = new DataflowVariable()
def energy = new DataflowVariable()

group.task {
    println "What is your weight?"
    mass << (inputReader.readLine() as int)
    println "Your energy is ${energy.val} J"
}

group.task {
    energy << mass.val * SPEED_OF_LIGHT ** 2
}

group.task {
    println "If you are shorter than ${mass.val + 100} cm, you should probably excercise more."
}