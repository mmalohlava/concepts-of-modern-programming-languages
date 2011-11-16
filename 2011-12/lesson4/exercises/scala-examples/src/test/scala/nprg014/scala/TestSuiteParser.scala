// NOTE: based on example from http://brianmckenna.org/blog/sexp_scala
package nprg014.scala
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers._

/*
 * Assignment: add support for variables
 * 
 */

class TestSuiteParser extends FunSuite {
  
  test("clojure-in-scala - simple expresion") {
	  
	  assert(Evaluator("(+ 2 (* 3 4))") === List(14))  
  }
  
  test("clojure-in-scala - wrong expression") {	  
	  
	  intercept[Exception] {
	   Evaluator("(+ 2 (** 1 4))") 
	  }
  }
	  
  test("clojure-in-scala - long expression") {
	  expect(List(91)) {
	    Evaluator("(- (- (+ (- (+ 99 11) 10) 11) 10) 10)")
	  }
  }
  
  test("clojure-in-scala - folding") {
	  expect(List(5)) {
	    Evaluator("(+ 1 1 1 1 1)")
	  }
  }
	  
  test("clojure-in-scala - list") {
	  expect(List(1, 1, 1)) {
	    Evaluator("(1 1 1)")
	  }
  }
    
 
  /* Assignment: introduce variables into the expression */
  
/*  test("clojure-in-scala - variable #1") {	  
	  expect(List(3)) {
	    Evaluator("(+ 2 x)", Map( 'x -> 1.0 ))
	  }
  }

  test("clojure-in-scala - variable #2") {
	  expect(List(10052)) {
	    Evaluator("(+ 2 (* z (+ A 5)))", Map( 'z -> 10.0, 'A -> 1000. ))
	  }  
  }
  
  test("clojure-in-scala - variable #3") {
      val result = Evaluator("(A z 1 0)", Map( 'z -> 10.0, 'A -> 1000. ))
      
      result should be (List(1000, 10, 1, 0))
  }*/
  
  test("simple plus expression parser") {
    import util.parsing.combinator.JavaTokenParsers

    /* Optional Assignment: introduce support for the rest of operations (-, *, /) */
    object PlusExpressionParser extends JavaTokenParsers {
      def expr: Parser[Any] = term ~ rep("+" ~ term)
      def term: Parser[Any] = wholeNumber | ident
    }
    
    println(PlusExpressionParser.parseAll(PlusExpressionParser.expr, "1+2+x"));
  }	
}

import util.parsing.combinator.RegexParsers

sealed trait Node
case class SExp(l: List[Node]) extends Node
case class SInt(i: Int) extends Node
case class SIdent(s: String) extends Node

object Parser extends RegexParsers {  
  def int = regex("""[0-9]+""".r) ^^ { (i: String) => SInt(i.toInt) } // NOTE: ^^ means semantic action
  def ident = regex("""[A-Za-z+-/\*]+""".r) ^^ SIdent // NOTE: regular expression
  def sexp = "(" ~> rep(node) <~ ")" ^^ SExp // NOTE: ~ means sequence, ~>, rep means repetition - i.e, rep(node) = node*
  def node: Parser[Node] = int | ident | sexp // NOTE: | means 
  def apply(s: String) = { 
    val result = parse(sexp, s)
    result match {
      case Success(p, _) 	=> p            
    }    
  }
}

object Evaluator {
  def eval(n: Node, env: Map[Symbol, Double]):List[Double] = {
    n match {
      
      case SExp(Nil) => Nil
      
      case SExp(x :: xs) => {
        // evaluate each parameter separately
        val evaluatedXs = xs.flatMap( eval(_, env) )
        val result = 
	        x match {
	          	case SIdent("+") => List((evaluatedXs.first /: evaluatedXs.tail)(_ + _))
	          	case SIdent("-") => List((evaluatedXs.first /: evaluatedXs.tail)(_ - _))
	          	case SIdent("*") => List((evaluatedXs.first /: evaluatedXs.tail)(_ * _))
	          	case SIdent("/") => List((evaluatedXs.first /: evaluatedXs.tail)(_ / _))
	          	case e => 	          	  List(e).flatMap(eval(_, env)) ::: evaluatedXs	          	
	          }
        
        result        
      }      
      case SInt(x) => List(x)
    }
  }
  
  def apply(program: SExp, env: Map[Symbol, Double]):List[Double] = eval(program, env);
  def apply(s: String):List[Double] = apply(Parser(s), Map.empty[Symbol, Double]);  
}


