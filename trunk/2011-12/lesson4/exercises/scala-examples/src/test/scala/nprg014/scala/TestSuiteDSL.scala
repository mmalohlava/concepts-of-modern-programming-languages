package nprg014.scala

import org.scalatest.FunSuite

class TestSuiteDSL extends FunSuite {
  
  test("distance DSL") {
    // local import
    import distanceDSL._;
    
    expect(1-->m) {
     (50-->cm) + (50-->cm) 
    }  
    
    assert(((1-->mm + (3-->m)) to m) === (3.001-->m))
  }
  
  test("assignment distance DSL - extended") {
    // local import
    import distanceDSL._;
    
//    assert(((1{mm} + 3{m}) to m) === (3.001{m}))
    pending
  }
  
}


object distanceDSL {
	val baseUnit = mm;
	
	class DistanceUnit(val name:String, val factorToMM:Long) 
	
	object mm extends DistanceUnit("mm", 1)
	object cm extends DistanceUnit("cm", 10)
	object m extends DistanceUnit("m", 1000)
	object km extends DistanceUnit("km", 1000000L)
	
	/* Immutable structure */
	class DistanceValue(val value : Double, val unit: DistanceUnit) {
	  val valueInMM = value * unit.factorToMM;
	      
	  def to (targetUnit: DistanceUnit):DistanceValue = {	    
	    val targetValue = valueInMM / targetUnit.factorToMM;
	    
	    new DistanceValue(targetValue, targetUnit);	    
	  }
	  	  
	  def + (that: DistanceValue):DistanceValue = {
	    new DistanceValue(this.valueInMM + that.valueInMM, mm); 
	  }
	  
	  override def toString:String = value + " " + unit.name;
	  
	  override def equals(that : Any):Boolean = {
	    if (that.isInstanceOf[DistanceValue])
	    	this.valueInMM == that.asInstanceOf[DistanceValue].valueInMM
    	else
    		false
	  }
	}
	
	class DistanceValueWrapper(val value: Double) {
	  def -->(unit: DistanceUnit): DistanceValue = new DistanceValue(value, unit);
	}
	
	
	implicit def intToDistanceValue(value: Double):DistanceValueWrapper = new DistanceValueWrapper(value);
//implicit def intToDistanceValue(value: Int) = new DistanceValueWrapper(value);
	
}
