import groovy.transform.InheritConstructors
import groovy.transform.TupleConstructor
import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor
import static groovyx.gpars.actor.Actors.actor

@Singleton final class GetMessage {}

@TupleConstructor
abstract class RegistrationDetails {
    final String name
    final Actor originator
}

@InheritConstructors final class Registration extends RegistrationDetails {}

@InheritConstructors final class ConfirmedRegistration extends RegistrationDetails {
    @Override
    public String toString() { name }
}

final idGenerator = actor {
    loop {
        react {
            sleep 100
            it << System.currentTimeMillis()
        }
    }
}

class Screener extends DefaultActor {
    private final suspects = ['Joe the hacker', 'Alice the mad', 'Dave the coder']

    void act() {
        loop {
            react { registration ->
                if (!suspects.contains(registration.name)) {
                    reply new ConfirmedRegistration(registration.name, registration.originator)
                    registration.originator << "Validated"
                } else {
                    registration.originator << "Not accepted"
                }
            }
        }
    }
}

final screener = new Screener().start()

final event = actor {
    def registrations = []

    loop {
        react {
            switch (it) {
                case GetMessage:
                    reply registrations.asImmutable()
                    break
                case Registration:
                    screener << it
                    reply "Request has been sent for validation"
                    break
                case ConfirmedRegistration:
                    registrations << it
                    it.originator << "Registerred successfully. Expect your id shortly"
                    idGenerator << it.originator
            }
        }
    }
}

@TupleConstructor
class Attendee extends DefaultActor {
    final String name
    final Actor event
    private long registrationId

    void act() {
        event << new Registration(name, this)
        react { msg1 ->
            println "Attendee $name: " + msg1
            react { msg2 ->
                println "Attendee $name: " + msg2
                if (msg2 == 'Validated') {
                    react { msg3 ->
                        println "Attendee $name: " + msg3
                        react { idMessage ->
                            println "Attendee $name: Received my registration id " + idMessage
                            registrationId = idMessage
                        }
                    }
                }
            }
        }
    }
}
new Attendee('Joe', event).start()
new Attendee('Dave', event).start()
new Attendee('Alice the mad', event).start()

sleep 1000

event.sendAndContinue(GetMessage.instance, { println "Asynchronous hook: " + it })
println "Direct result: " + event.sendAndWait(GetMessage.instance)