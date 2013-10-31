package E06;

import scala.ScalaObject;
import scala.Predef.;
import scala.runtime.BoxesRunTime;

public final class Functions$ implements ScalaObject {

   public static final Functions$ MODULE$;


   static {
      new Functions$();
   }

   public void main(String[] args) {
      Integer a = null;
      byte b = 3;
      a = BoxesRunTime.boxToInteger(b);
      .MODULE$.println(a);
      System.out.println(b);
   }

   private Functions$() {
      MODULE$ = this;
   }
}
