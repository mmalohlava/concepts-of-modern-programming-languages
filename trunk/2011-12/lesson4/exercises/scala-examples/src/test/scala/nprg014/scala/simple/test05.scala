package nprg014.scala.simple
import org.scalatest.FunSuite

class TestSuite05Collections extends FunSuite {
  
  test("lists") {
    
    assert(Nil.isEmpty === true)
    
    val list = List(1, 2, 3, "four");
    
    assert(list.size === 4);
    // access fields
    assert(list(2) === 3);    
    assert(list(3) === "four");    
    // groovy access does not work 
    intercept[IndexOutOfBoundsException] {
    	assert(list(-1) === "four");
    }
    assert(list.head === 1);
    assert(list.tail === List(2,3,"four"));
    assert(list.last === "four");
    assert(list.init === List(1,2,3));
    
    assert(list.reverse === List("four", 3, 2, 1));
    assert(list.drop(2) === List(3,"four"));
    
    
    
    val list2 = list ::: List("five", "six");
    assert(list2.size === 6);
    assert(list2(3) === "four");
    assert(list2(4) === "five");
    
    val list3 = "zero" :: list2;
    assert(list3.size === 7);
    assert(list3(0) === "zero");
    assert(list3(4) === "four");
    assert(list3(5) === "five");
    
    val list4 = 1 :: 2 :: 3 :: Nil; // Nil has to be on the end
    assert(list4.size === 3);    
  }
  
  test("sets") {
    val set1 = Set("GB", "FR", "CR")
    assert(set1.size === 3);
    
    val set2 = set1 + "USA"
    assert(set2.size === 4);
    
    val set3 = set2 + "CR"
    assert(set3.size === 4);
    
    // union
    val set4 = set3 ++ Set("BR", "VE");
    assert(set4.size === set3.size + 2);    
    
    // intersection
    val set5 = set4 & Set("GB", "SK"); 
    assert(set5 === Set("GB"));
  }
  
  test("maps") {
    // Heterogeneous map
    val ordinals = Map(0 -> "zero", "1" -> "one", 2 -> "two");
    assert(ordinals.size === 3)
    assert(ordinals(0) === "zero")
    assert(ordinals("1") === "one")
    
    // scala maps are imuttable
    //ordinals += 3 -> "three"
    
    import scala.collection.mutable.HashMap;
    val map1 = new HashMap[Int, String];
    map1 += 1 -> "one";
    map1 += 0 -> "zero";
    assert(map1.size === 2)
    assert(map1(0) === "zero")
    intercept[NoSuchElementException] {
      ordinals(3) 
    }
  }
  
  test("functional collections") {
    // filter, count ...
    // foldleft
  }
}