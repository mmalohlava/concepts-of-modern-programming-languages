package nprg014.scala
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers._
import scala.xml.NodeSeq
import java.io.File

class TestSuiteXML extends FunSuite {
  
  val scalaUrl= "http://scala-lang.org"
  val scalaTitle = "Scala"
  
  def html =
      <body> 
         <h1>This is an XML included in scala.</h1>
         <p>
		  	You can find more examples at <a href="{scalaUrl}" alt="{scalaTitle.toUpperCase}" >Scala pages</a>
		 </p>
  		 <h1>{scalaTitle}</h1>
  		 <p>This is the Scala language</p>
      </body>
  
  test("xml #0 - XML queries") {
    val result = html \ "a"
    
    result should have length (0)    
  }
  
  test("xml #1 - XML queries") {
    
    val result = html \\ "a"
    
    result should have length (1)
    result(0) should be (<a href="{scalaUrl}" alt="{scalaTitle.toUpperCase}" >Scala pages</a>)
    
  }

  test("xml #2 - XML queries") {
	val result = html \\ "a"
    
    result should have length (1)
    result(0) should be (<a href="{scalaUrl}" alt="{scalaTitle.toUpperCase}" >Scala pages</a>)  
  }
  
  test("xml #3 - XML queries") {
    val result = 
    html match {
      case <body>{contents @ _*}</body> => for(element @ <h1>{_*}</h1> <- contents) yield element.text //match only p.elements
    }    
    
    result should be (List("This is an XML included in scala.", "Scala"))    
  }
  
  test("xml #4 - saving/loading xml from file") {
    val filename = "TestSuiteXML#4.html"
      
    scala.xml.XML.save(filename, html)
    
    val f = new File(filename);
    f should be ('exists)
    
    val loadedHtml = scala.xml.XML.loadFile(filename)
    
    loadedHtml  should be (html)
  }
  
  test("xml #5 - load ANT file") {
    val filename = "src/test/resources/common.xml"
    /* Assignment - load an ANT file stored in src/test/resources/common.xml, print all targets and their dependencies */
        
    pending       
  }
  
  
}