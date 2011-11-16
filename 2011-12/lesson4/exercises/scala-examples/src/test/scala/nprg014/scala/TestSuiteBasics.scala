package nprg014.scala
import org.scalatest.FunSuite

class TestSuiteBasics extends FunSuite {

  test("note #1: everything is a function call #1") {
    val l = 1 + 3
    val r = (1).+(3)

    assert(l === r) // it is possible to use == however, it does not write specific error message
    assert("AAAA" === ("aaaA" toUpperCase)) // the dot is not required
  }

  test("note #2: == compare values (not references as in Java)") {
  }

  test("note #3: the function call (function definition inside a function) ") {
    def func1(x: Int) = x
    def func2(x: Int) = { x } // NOTE: the last expression express return value
    def func3(x: Int): Int = { return x }
    def proc(x: Int) { val a = 1 } // NOTE: the procedure style 

    assert(func1(1) === func2(1))
    assert(func2(1) === func3(1))
    assert(proc(3) === ())

    // func call
    assert(func1 { 1 } === func2(1)) // NOTE: in method call with one parameter it is possible to use {} as well as () 
  }

  test("note #4: for-yield") {
    val args = scala.util.Random.shuffle((1 to 1000) toList)

    val evenNumBetween256and260 = for {
      i <- args
      if i > 256
      if i < 260
      if i % 2 == 0
    } yield i.toString()

    assert(evenNumBetween256and260 === List("258"))
  }

  test("note #5: methods can be used as postfix operators") {
    val s = "John Malkovich"

    assert(s.toLowerCase === (s toLowerCase))
  }

  test("note #6: prefix operators are special methods (only +-!~ are allowed to be used as postfix operator)") {
    val num = +2

    assert(+num === (num).unary_+)
  }

  ignore("note #7: infix operators are methods - see note #1") {
  }

  test("note #8: simple matching on parameters") {
    val arg = "gbp"

    val name =
      arg match {
        case "gbp" => "pounds"
        case "eur" => "euro"
        case _ => "?"
      }

    assert(name === "pounds")

    val anyArg: Any = true
    anyArg match {
      case s: String => assert(s.isInstanceOf[String])
      case n: Int => assert(n.isInstanceOf[Int])
      case b: Boolean => assert(b.isInstanceOf[Boolean])
    }

    val tuple = ("John", "Wayn")
    //    val tuple = ("JohnX", "JohnX")
    tuple match {
      case ("John", x) => assert(x === "Wayn")
      // case(x, x) does not work. Try it!
      case (x, y) if (x == y) => assert(x === "JohnX"); assert(y === "JohnX")
    }
  }

  ignore("note #9: val v. var") {
  }

  
  class Person(val name: String, val surname: String, profession: String) {
    
      private val fullName: String = name + " " + surname;

      // another constructor
      def this(name: String) {
        this(name, "", "unknown");
      }

      override def toString() = fullName;
    }

    /* companion object */
    object Person {
      val internalPerson = new Person("John")
      // access of private member of the class Person
      def fullName = internalPerson.fullName;
    }

    object PersonX {
      val internalPerson = new Person("John")
      // cannot access private member of the class Person, because the object
      // PersonX is not companion object
      //      def fullName = "Fullname is " + internalPerson.fullName;     
  }
  
  test("note #9: it is still like Java+C#+Groovy BUT...") {
    val john = new Person("John", "Malkovitch", "actor");
    
    assert(john.toString() === "John Malkovitch");
    assert(john.name === "John");    
    
  }

  /* Assignment: write the code which will satisfy the test */
  test("assignment: operators") {
    val code = '*';

    // val bills = ?YOUR ASSIGNMENT"
    //    assert(bills !*&^%~ code! === 42)
    pending
  }

}