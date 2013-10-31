package E03;

import java.io.PrintStream;
import scala.ScalaObject;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "E2A!\t1Aj\\4hKJTaAB4A\nqCA\rA!BAaM\\4-\tAA[1wC&Q\nTWm;=R\"\tE\tQa]2bYL!a\tM\r\\1PE*,7\r\t+\t)A-)q[3sKBqCG1)DCS>L!a\rAN;TiJ,-;!\tAHy%t }\tC\"B1\"B\t!a7pORQ\tJ!a\n\t\tUsS\t\rAK[N<CA/\tyA&.!1K]3eKL!a\f\rM#(/8h\ti"
)
public class Logger implements ScalaObject {

   private final PrintStream where;


   public void log(String msg) {
      this.where.println(msg);
   }

   public Logger(PrintStream where) {
      this.where = where;
   }
}
