import groovyx.gpars.dataflow.DataflowVariable
import static groovyx.gpars.dataflow.Dataflow.task

class RegistrationRequest {
    String name
    final DataflowVariable outcome = new DataflowVariable()
}

final register = {registrationRequest ->
    sleep 2000  //Evaluating the request
    if (registrationRequest.name.startsWith('A')) {
        registrationRequest.outcome << false
    } else {
        registrationRequest.outcome << true
    }
}

final joe = new RegistrationRequest(name: 'Joe')
final dave = new RegistrationRequest(name: 'Dave')
final alice = new RegistrationRequest(name: 'Alice')

dave.outcome.whenBound {println it ? "We are glad to announce that Dave has passed!" : "Never mind"}

//TODO switch to using eachParallel() instead of tasks here
[joe, dave, alice].each {participant ->
    task {
        register participant
    }
}

println joe.outcome.get()
println dave.outcome.get()
println alice.outcome.get()

//TODO modify the RegistrationRequest class so that it prints out a message automatically once the outcome gets bound
