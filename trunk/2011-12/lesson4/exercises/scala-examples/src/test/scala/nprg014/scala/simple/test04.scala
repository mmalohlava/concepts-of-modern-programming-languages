package nprg014.scala.simple
import org.scalatest.FunSuite

class TestSuite04Traits extends FunSuite {
  
//  - extends means the target of mixin
//  - trait can call super.put(2*x) (absttract override)
//    - more mixinx is evaluated from right-to left 
  
  // idea #1: trait ktery bude modifikovat chovani person 
  // tj. budeme mit operatory +
  //     a pak mit trait jako married, single 
  
  /*trait Person[T] {
	  def name:T;
	  def surname:T;
	  def fullname:T = surname + separator + name; // T has to implement + operation
	  
	  protected def separator:T;  
  }  */
  
  test("Scala traits") {   
  }

}