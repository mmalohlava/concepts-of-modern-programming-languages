package nprg014.scala.simple
import org.scalatest.FunSuite

class TestSuite02 extends FunSuite {
  
  test("ranges") {
    val range1 = 0 to 10;
    assert(range1.start === 0)
    assert(range1.end === 10)
    assert(range1.last === 10)
    
    val range2 = 0 until 10;
    assert(range2.start === 0)
    assert(range2.end === 10)
    assert(range2.last === 9)
    
    
    val range3 = 10 to 0
    assert(range3.start === 10)
    assert(range3.end === 0)
    assert(range3.length === 0)
    
    val range4 = (10 to 0) by -1
    assert(range4.start === 10)
    assert(range4.length === 11)
    
    val range5 = 'a' to 'z' // 'A' to 'a' works also, but 'a' to 'A' does not work
    assert(range5.length === 26)
    
  }
  
  test("tuples") {
    val person = ("John", "Malkovich", "actor");
    assert(person._1 === "John")
    
    val (name, surname, _) = person
    assert(name === "John")
    assert(surname ===  "Malkovich")
    
  }

}