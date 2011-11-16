package nprg014.scala.simple
import org.scalatest.FunSuite

class TestSuite08XML extends FunSuite {
  
  test("bounds") {
    
    trait Able[S] {
      type X = S
    } 
    
    abstract class A [T <: Able[_]] {
      type I = T#X
      def print = println(classOf[I])
      def set(i: I):T
    }
    
    class ZT extends Able[Int];
    
    object a extends A[ZT] {
      def set(i: I) = {
        print
        new ZT
      }
    }
    
    a.set("A")
  }
    
}