import scala.MatchError;
import scala.ScalaObject;
import scala.Tuple2.mcII.sp;
import scala.collection.immutable.List;
import scala.collection.immutable..colon.colon;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;

public final class TailRecursion$ implements ScalaObject {

   public static final TailRecursion$ MODULE$;


   static {
      new TailRecursion$();
   }

   public boolean contains(List list, String value) {
      while(true) {
         boolean var10000;
         if(list instanceof colon) {
            label22: {
               colon var5 = (colon)list;
               List var6 = var5.tl$1();
               Object var7 = var5.hd$1();
               if(value == null) {
                  if(var7 == null) {
                     break label22;
                  }
               } else if(value.equals(var7)) {
                  break label22;
               }

               list = var6;
               continue;
            }

            var10000 = true;
         } else {
            label28: {
               if(.MODULE$ == null) {
                  if(list == null) {
                     break label28;
                  }
               } else if(.MODULE$.equals(list)) {
                  break label28;
               }

               throw new MatchError(list);
            }

            var10000 = false;
         }

         return var10000;
      }
   }

   public int length(List list) {
      int var10000;
      if(list instanceof colon) {
         var10000 = 1 + this.length(((colon)list).tl$1());
      } else {
         label17: {
            if(.MODULE$ == null) {
               if(list == null) {
                  break label17;
               }
            } else if(.MODULE$.equals(list)) {
               break label17;
            }

            throw new MatchError(list);
         }

         var10000 = 0;
      }

      return var10000;
   }

   public int length2(List list, int lenSoFar) {
      while(list instanceof colon) {
         List var10000 = ((colon)list).tl$1();
         ++lenSoFar;
         list = var10000;
      }

      if(.MODULE$ == null) {
         if(list == null) {
            return lenSoFar;
         }
      } else if(.MODULE$.equals(list)) {
         return lenSoFar;
      }

      throw new MatchError(list);
   }

   public int length2$default$2() {
      return 0;
   }

   public void main(String[] args) {
      List list = scala.collection.immutable.List..MODULE$.apply(scala.Predef..MODULE$.wrapRefArray((Object[])(new String[]{"aa", "bb", "cc", "dd"})));
      scala.Predef..MODULE$.println(BoxesRunTime.boxToBoolean(this.contains(list, "bb")));
      scala.Predef..MODULE$.println(BoxesRunTime.boxToInteger(this.length2(list, this.length2$default$2())));
      new sp(1, 2);
   }

   public void b() {}

   public int a() {
      return 3;
   }

   private TailRecursion$() {
      MODULE$ = this;
   }
}
