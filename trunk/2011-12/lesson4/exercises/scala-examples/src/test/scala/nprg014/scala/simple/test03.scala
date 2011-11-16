package nprg014.scala.simple
import org.scalatest.FunSuite

class TestSuite03 extends FunSuite {
  
  class Person(val name: String, val surname:String, profession: String) {
      private val fullName:String = name + " " + surname;
      
      // another constructor
      def this (name:String) {
        this(name,"", "unknown");        
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
  
  test("Scala classes") {   
    val john = new Person("John", "Malkovitch", "actor");
    
    assert(john.toString() === "John Malkovitch");
    assert(john.name === "John");
//    assert(john.profession === "actor") // does not work due to profession is not implicitly visible
    
  }
  
  test("Scala objects") {
    // john is singleton
    object john extends Person("John", "Malkovitch", "actor")
    
    assert(john.toString === "John Malkovitch")    
  }
  
  test("Companion objects") {
    assert(Person.fullName === "John ")    
  }

}