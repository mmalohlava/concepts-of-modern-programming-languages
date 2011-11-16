package nprg014.scala.simple
import org.scalatest.FunSuite

class TestSuite06 extends FunSuite {
     
  test("preconditions") {
    class Rational(n:Int, d: Int) {
      require(d != 0) // Predef package
    };
    
    intercept[IllegalArgumentException] {
      new Rational(3,0);
    }
  }
  
  // TODO postconditions via ensure
    
}