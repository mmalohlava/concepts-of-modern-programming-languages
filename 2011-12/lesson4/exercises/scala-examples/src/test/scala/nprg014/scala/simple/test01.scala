package nprg014.scala.simple

import org.scalatest.FunSuite
import scala.util.Random
import java.io.FileNotFoundException
import java.io.IOException
import java.io.FileReader

class TestSuite01Basics extends FunSuite {

  test("everything is a function call #1") {
    val l = 1 + 3
    val r = (1).+(3)
            
    assert(l === r)    // it is possible to use == however, it does not write specific error message
  }

  test("everything is a function call #2") {
    val r = (3)*1.-(2) // 1. is interpreted as double number
    
    assert(r === 1)
  }
  
  test("for cycle") {
    val args = Array("a", "b", "c");
    
	  for(i <- 0 until args.length) {	
//		for(i <- 0 to args.length) {
		  println(args(i))
//		  println(i);
		}				
	  	
	  	for(arg <- args) {
	  	  println(arg);
	  	}
  }
  
  test("foreach cycle") {
    val args = Array("a", "b", "c");
    var i = 0;
    
    args.foreach(println) 
    args.foreach(argv => i = i + 1)
        
    assert(i === args.length)
  }
  
  test("for-yield") {
    val args = scala.util.Random.shuffle ( (1 to 1000) toList)
    val evenNumBetween256and260 = for {
      i <- args
      if i > 256
      if i < 260
      if i % 2 == 0
    } yield i.toString()
    
    assert(evenNumBetween256and260 === List("258"))        
  }
    
  
  test("a method inside test") {
    def foo:Boolean = {
      val endVal = 3
      for(i <- 0 until endVal) {
        println(i)
      }
      true // or use return true
//      return false
    } 
    
    assert(foo)
  }
  
  ignore("raw strings") {
    val longString = """|This is avery loooong
                        | string.""".stripMargin;
  }
  
  test("postfix operators") {
    val s = "John Malkovich"
      
    assert(s.toLowerCase === (s toLowerCase))
  }
  
  test("prefix operators") {
    val num = +2
      
    assert(+num === (num).unary_+)
  }
  
  ignore("operators") {
    val code = "A";
//    assert(bills !*&^%~ code! === (bills.!*&^%~(code)).!())
  }
  
  test ("try-block") {
    try {
      val f = new FileReader("input.txt")      
    } catch {
      case ex: FileNotFoundException =>  println("FileNotFound") 
      case ex: IOException => println("IOException " + ex.getMessage());
      case _ => println("Something goes wrong...")
    } 
  }
  
  test ("try-block-yielding-value") {
    def f(): Int = try { return 1 } finally {return 2}
    assert( f === 2 );
    
    def g(): Int = try { 1 } finally { 2}
    assert( g === 1 );    
  }
  
  test("match") {
    val arg = "gbp"
      
    val name = 
      arg match {
		  case "gbp" => "pounds"
		  case "eur" => "euro"
		  case _ 	 => "?"		    
		}
    
    assert(name === "pounds")
  }
}