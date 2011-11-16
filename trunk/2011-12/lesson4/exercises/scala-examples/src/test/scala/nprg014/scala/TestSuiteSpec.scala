package nprg014.scala
import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import scala.collection.mutable.Stack
import org.scalatest.matchers.ShouldMatchers._

class TestSuiteSpec extends FeatureSpec with GivenWhenThen {
 
  feature("The user can pop an element off the top of the stack") {
 
    info("As a programmer")
    info("I want to be able to pop items off the stack")
    info("So that I can get them in last-in-first-out order")
 
    scenario("pop is invoked on a non-empty stack") {
 
      given("a non-empty stack")
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      val oldSize = stack.size
 
      when("when pop is invoked on the stack")
      val result = stack.pop()
 
      then("the most recently pushed element should be returned")
//      assert(result === 2)
      result should be (2)
 
      and("the stack should have one less item than before")
//      assert(stack.size === oldSize - 1)
      stack should have size (oldSize - 1)
    }
 
    /* 
     * Assignment - complete the following specification 
     */
    scenario("pop is invoked on an empty stack") {
       
      given("an empty stack")
      val emptyStack = new Stack[String]

      when("when pop is invoked on the stack")
      then("NoSuchElementException should be thrown")
      intercept[NoSuchElementException] {
        emptyStack.pop()
      }

      and("the stack should still be empty")
      assert(emptyStack.isEmpty)
//      pending
    }
  }
}