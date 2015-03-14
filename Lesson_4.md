# Lesson 4 details #

## Preparation ##

  * Install Java (if you don't have it installed) from [the Java download site](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  * Install Eclipse
  * In Eclipse, install Scala-IDE from the update site

## Resources ##

Grab the [lecture 3-4.zip](http://code.google.com/p/concepts-of-modern-programming-languages/downloads/detail?name=lecture3-4.zip&can=2&q=) file to get code examples to work with during the lesson.

## Homework ##

The goal is to implement a class (Monitor) that checks for correct ordering of events. The ordering is specified in form of internal DSL (in class MyMonitor).

The ordering is specified as a number of named properties (each being a rule what should hold for a sequence of events). A property is specified using require clauses, which have the following semantics:

- require matches the first event that can be matched (skips events that cannot be matched)

- nested require continues with the event that follows the event matched by its parent

The class below show the use of such a checker (down in the main method) and also provides most parts of the checker. What is left up to you is to define the header of the "property" function, body of the "require" function, and a property in the DSL that requires that the first Command event in the event list does not fail.

```
package H2
import scala.collection.mutable.MutableList

abstract class Event
case class Command(cmdName: String) extends Event
case class Succeed(cmdName: String) extends Event
case class Fail(cmdName: String) extends Event

class Property(val name: String, val func: () => Boolean)

class Monitor[T] {
	val properties = MutableList.empty[Property]
	
	def property /* Add declaration here */ {
		properties += new Property(propName, formula _)
	}
	
	var eventsToBeProcessed = List[T]()
	
	def check(events: List[T]) {
		for (prop <- properties) {
			eventsToBeProcessed = events
		
			val result = prop.func()
			
			println("Property \"" + prop.name + "\" ... " + (if (result) "OK" else "FAILED"))
		}
	}

	def require(func: PartialFunction[T, Boolean]): Boolean = {
		/* Add a body here
		 * 
		 * to know whether a partial function is defined for a given event,
		 * use func.isDefinedAt(event).
		 */		
	}
	
}

class MyMonitor extends Monitor[Event] {
	property("The first command should succeed or fail before it is received again") {
		require {
			case Command(c) => 
				require {
					case Succeed(`c`) => true
					case Fail(`c`) => true
					case Command(`c`) => false
				}
		}
	}

	property("The first command should not get two results") {
		require {
			case Succeed(c) => 
				require {
					case Succeed(`c`) => false
					case Fail(`c`) => false
					case Command(`c`) => true
				}
			case Fail(c) => 
				require {
					case Succeed(`c`) => false
					case Fail(`c`) => false
					case Command(`c`) => true
				}
		}
	}

	property("The first command should succeed") {
		/* Add a property definition here which requires that the first command does not fail.
		 * It should yield OK with the events listed in the main method.
		 */
		require {
			case Command(c) => 
				require {
					case Succeed(`c`) => true
					case Fail(`c`) => false
				}
		}
	}
}

object Checker {

	def main(args: Array[String]) {
		val events = List(
			Command("take_picture"),
			Command("get_position"),
			Succeed("take_picture"),
			Fail("take_picture")
		)
		
		val monitor = new MyMonitor
		monitor.check(events)
	}

}
```