# Lesson 3 details #

## Preparation ##

  * Install Java (if you don't have it installed) from [the Java download site](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  * Install Scala-IDE from http://scala-ide.org/download/sdk.html (take version for Scala 2.10.4)

## Resources ##

Grab the http://d3s.mff.cuni.cz/teaching/lang_concepts/files/lecture3-4.zip file to get code examples to work with during the lesson.

## Homework ##

```
// Add necessary class, object, and implicit def definitions in order to make the statements in the main work.

// ...
// ...
// ...

object ComplexNumbers {
	// ...

	
	def main(args: Array[String]) {
		
		println(Complex(1,2)) // 1+2i

		println(1 + 2*I + I*3 + 2) // 3+5i

		val c = (2+3*I + 1 + 4*I) * I
		println(-c) // 7-3i
	}
}
```