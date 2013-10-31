package E03;

import E03.Logger;
import scala.ScalaObject;
import scala.Console.;

public final class AppLogger$ extends Logger implements ScalaObject {

   public static final AppLogger$ MODULE$;


   static {
      new AppLogger$();
   }

   private AppLogger$() {
      super(.MODULE$.out());
      MODULE$ = this;
   }
}
