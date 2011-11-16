package nprg014.scala
import org.scalatest.FunSuite
import java.io.File
import java.io.PrintWriter

class TestSuiteFunctions extends FunSuite {
  
  test("note #1 - Scala has a concept of first-class functions ") {
    // functions
    val inc = (x:Int) => x + 1;    
    assert(inc(5) === 6)
    
    val dec10 = (x:Int) => {
    	val y = x - 5
    	y - 5
    }    
    assert(dec10(10) === 0)    
    
    val mul = (_:Int) * (_:Int) // NOTE: multiple underscores means multiple parameters. The type has to be specified
    assert(mul(4, 2) === 8)   
    
  }
  
  test("note #2 - first class functions are for functional style of programing - e.g., collections") {
    val list = List(1, 2, 3, 4, 5,6, 7, 8, 9)
    
    assert(list.filter((x:Int) => x % 2 == 0) === List(2, 4, 6, 8))
    
    val listNum = List(1, 2, 3, 4, 5,6, 7, 8, 9)
    val listNames = List("John", "Joe", "Johannes", "Joel", "Janis")
    
    assert(listNum.filter((x) => x > 8) === List(9)) // NOTE: type of x is inferred
    assert(listNames.filter((x) => x > "Johannes") === List("John"))   
    assert(listNum.filter(_ < 3) === List(1,2))
  }  
  
  
  test("note #3 - it is possible to define partial functions ") {
    def testPythagorasTuple(a:Int, b:Int, c:Int) = c*c == a*a + b*b;
    
    val func1 = testPythagorasTuple (_, _, _) // NOTE: func1 is implementing trait Function3 with method apply     
    assert (func1(3,4,5) === true)
    assert (func1(3,4,6) === false)
    assert (func1.apply(3,4,5) === true) // NOTE: apply method correspond to a function call
    assert (func1.apply(3,4,6) === false)
    
    val func2 = testPythagorasTuple _ // NOTE: explicit underscore is required    
    assert (func2(3,4,5) === true)
    
    
    val func3 = testPythagorasTuple(_:Int, _:Int, 13) // partial function
    val tuples =
      for {a <- 1 to 12
           b <- 1 to 12
           if a<b
           if func3(a, b)
      } yield (a,b)
    
    assert(tuples === List((5,12)))
  }
  
  test("note #4 - Scala support closures") {
    // NOTE: (x:Int) => x + 1 is a partial function without free variable. It represents closed term.
    // NOTE: (x:Int) => x + more; is an open term (has free variables), more has to be bound, 
    //       the resulting closure references the more when the closure was created
    
    def makeInc(increment : Int) = (x:Int) => x + increment;
    
    val inc1 = makeInc(1) // NOTE: closure creation, bind free variable increment to 1
    assert(inc1(9) === 10)

    var increment = 11;
    val inc11 = makeInc(increment)
    assert(inc11(9) === 20)
    increment = 21
    val inc21 = makeInc(increment)
    assert(inc21(9) === 30)    
  }
  
  test("note #5 - rearranging variable's location (heap v. stack) during closure creation") {
    def makePlotter(plotLabelTransformer: String => String) = {
      val plotLabels = List("Hello", "World", "!");
      
      (x:Int) => {
        var tempList = plotLabels; // reference local variable, has to be rearranged!
        for(i <- 1 to x) { 
        	tempList = tempList.map(plotLabelTransformer)
        }
        tempList
      }
    } 
    
    val plotter1 = makePlotter( x => "*"+x+"*"); 
    assert(plotter1(2) === List("**Hello**", "**World**", "**!**"))
  }
  
  test("note #6 - variable number of function parameters") {
    def listConstructor(args: String*) = { 
      val list =
      	 for{arg <- args
      		  if arg.length() < 5        
      	 	} yield arg
      list
    }
    
    assert(listConstructor("a", "b", "long world") === List("a", "b"))
  }
  
  test("note #7 - curried functions") {
    def sum(a:Int)(b:Int) = a+b;
    
    assert(sum(2)(3) === 5);
    
    val plus3 = sum(3)(_) // NOTE: sum(3) _ as well as sum(3)_ is allowed, however println_ is not a legal
    assert(plus3(2) === 5)    
  }
  
  test("note #8 - the functions are passed as instances implementin FunctionX trait ") {
    val customAssertEnabled = false
    
    def boolAssert(cond: Boolean) = if (customAssertEnabled && !cond) throw new AssertionError 
    def myAssert(cond: => Boolean) = if (customAssertEnabled && !cond) throw new AssertionError
    
    // NOTE: custom assertion is disabled
    intercept[ArithmeticException] {
      boolAssert( 10 / 0 == 0) // NOTE: the condition is evaluated before method call 
    }    
    myAssert( 10 / 0 == 0) // NOTE: the condition is evaluated inside method call via creation a function with body => Boolean, and call its apply method and the place of using the condition    
  }
  
  test("note #8 - function as parameter") {
    
    def tester(name:String, desc:String*)(f: =>Unit) { 
      println("Test " + name);
      println(" -- testing f = " + f )
      // invoking the given function
      (f _)() 
    }
    
    tester("printing") { 
      println("A")
      val a = 3;
      a;
      println("b")
    }
    tester("printing") (println)
    tester("printing", "testing println function")(println)    
  }
  
    
  test("assignment - implements withWritter method which will ensure closing the writter") {
    pending
//    withWritter(new File("date.txt")) {
//        writter => { // writter is PrintWritter
//          writter.println(new java.util.Date)          
//        }
//    }
  }
  
  

}