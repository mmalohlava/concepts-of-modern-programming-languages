package E02;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public class DynamicTypingMain implements GroovyObject {

   // $FF: synthetic field
   private static ClassInfo $staticClassInfo;
   // $FF: synthetic field
   public static transient boolean __$stMC;
   // $FF: synthetic field
   private transient MetaClass metaClass;
   // $FF: synthetic field
   private static SoftReference $callSiteArray;
   // $FF: synthetic field
   private static Class $class$E02$DynamicTypingMain;
   // $FF: synthetic field
   private static Class $class$java$lang$String;


   public DynamicTypingMain() {
      CallSite[] var1 = $getCallSiteArray();
      MetaClass var2 = this.$getStaticMetaClass();
      this.metaClass = var2;
   }

   public static void printValue(int value) {
      CallSite[] var1 = $getCallSiteArray();
      var1[0].callStatic($get$$class$E02$DynamicTypingMain(), var1[1].call("Integer: ", (Integer)DefaultTypeTransformation.box(value)));
   }

   public static void printValue(Object value) {
      CallSite[] var1 = $getCallSiteArray();
      var1[2].callStatic($get$$class$E02$DynamicTypingMain(), var1[3].call("Any: ", value));
   }

   public static Object getValue(int i) {
      CallSite[] var1 = $getCallSiteArray();
      return BytecodeInterface8.isOrigInt() && BytecodeInterface8.isOrigZ() && !__$stMC && !BytecodeInterface8.disabledStandardMetaClass()?((i * i + 1) % 2 == 0?"Hello world":(Integer)DefaultTypeTransformation.box(3)):(ScriptBytecodeAdapter.compareEqual(var1[4].call(var1[5].call(var1[6].call((Integer)DefaultTypeTransformation.box(i), (Integer)DefaultTypeTransformation.box(i)), (Integer)DefaultTypeTransformation.box(1)), (Integer)DefaultTypeTransformation.box(2)), (Integer)DefaultTypeTransformation.box(0))?"Hello world":(Integer)DefaultTypeTransformation.box(3));
   }

   public static void main(String ... args) {
      CallSite[] var1 = $getCallSiteArray();
      Integer value = null;
      String var3 = "Hello world";
      var1[7].callStatic($get$$class$E02$DynamicTypingMain(), var3);
      byte var4 = 3;
      value = (Integer)DefaultTypeTransformation.box(var4);
      var1[8].callStatic($get$$class$E02$DynamicTypingMain(), value);
      Object value1;
      if(!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
         Object var6 = getValue(1);
         value1 = var6;
      } else {
         Object var5 = var1[9].callStatic($get$$class$E02$DynamicTypingMain(), (Integer)DefaultTypeTransformation.box(1));
         value1 = var5;
      }

      var1[10].callStatic($get$$class$E02$DynamicTypingMain(), value1);
      if(!__$stMC && !BytecodeInterface8.disabledStandardMetaClass()) {
         Object var8 = getValue(2);
         value1 = var8;
      } else {
         Object var7 = var1[11].callStatic($get$$class$E02$DynamicTypingMain(), (Integer)DefaultTypeTransformation.box(2));
         value1 = var7;
      }

      var1[12].callStatic($get$$class$E02$DynamicTypingMain(), value1);
   }

   // $FF: synthetic method
   public Object this$dist$invoke$1(String name, Object args) {
      CallSite[] var3 = $getCallSiteArray();
      return ScriptBytecodeAdapter.invokeMethodOnCurrentN($get$$class$E02$DynamicTypingMain(), this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), $get$$class$java$lang$String()), ScriptBytecodeAdapter.despreadList(new Object[0], new Object[]{args}, new int[]{0}));
   }

   // $FF: synthetic method
   public void this$dist$set$1(String name, Object value) {
      CallSite[] var3 = $getCallSiteArray();
      ScriptBytecodeAdapter.setGroovyObjectField(value, $get$$class$E02$DynamicTypingMain(), this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), $get$$class$java$lang$String()));
   }

   // $FF: synthetic method
   public Object this$dist$get$1(String name) {
      CallSite[] var2 = $getCallSiteArray();
      return ScriptBytecodeAdapter.getGroovyObjectField($get$$class$E02$DynamicTypingMain(), this, (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[]{name}, new String[]{"", ""}), $get$$class$java$lang$String()));
   }

   // $FF: synthetic method
   protected MetaClass $getStaticMetaClass() {
      if(this.getClass() != $get$$class$E02$DynamicTypingMain()) {
         return ScriptBytecodeAdapter.initMetaClass(this);
      } else {
         ClassInfo var1 = $staticClassInfo;
         if(var1 == null) {
            $staticClassInfo = var1 = ClassInfo.getClassInfo(this.getClass());
         }

         return var1.getMetaClass();
      }
   }

   // $FF: synthetic method
   public MetaClass getMetaClass() {
      if(this.metaClass != null) {
         return this.metaClass;
      } else {
         this.metaClass = this.$getStaticMetaClass();
         return this.metaClass;
      }
   }

   // $FF: synthetic method
   public void setMetaClass(MetaClass var1) {
      this.metaClass = var1;
   }

   // $FF: synthetic method
   public Object invokeMethod(String var1, Object var2) {
      return this.getMetaClass().invokeMethod(this, var1, var2);
   }

   // $FF: synthetic method
   public Object getProperty(String var1) {
      return this.getMetaClass().getProperty(this, var1);
   }

   // $FF: synthetic method
   public void setProperty(String var1, Object var2) {
      this.getMetaClass().setProperty(this, var1, var2);
   }

   // $FF: synthetic method
   public static void __$swapInit() {
      CallSite[] var0 = $getCallSiteArray();
      $callSiteArray = null;
   }

   static {
      __$swapInit();
   }

   // $FF: synthetic method
   public void super$1$wait() {
      super.wait();
   }

   // $FF: synthetic method
   public String super$1$toString() {
      return super.toString();
   }

   // $FF: synthetic method
   public void super$1$wait(long var1) {
      super.wait(var1);
   }

   // $FF: synthetic method
   public void super$1$wait(long var1, int var3) {
      super.wait(var1, var3);
   }

   // $FF: synthetic method
   public void super$1$notify() {
      super.notify();
   }

   // $FF: synthetic method
   public void super$1$notifyAll() {
      super.notifyAll();
   }

   // $FF: synthetic method
   public Class super$1$getClass() {
      return super.getClass();
   }

   // $FF: synthetic method
   public Object super$1$clone() {
      return super.clone();
   }

   // $FF: synthetic method
   public boolean super$1$equals(Object var1) {
      return super.equals(var1);
   }

   // $FF: synthetic method
   public int super$1$hashCode() {
      return super.hashCode();
   }

   // $FF: synthetic method
   public void super$1$finalize() {
      super.finalize();
   }

   // $FF: synthetic method
   private static void $createCallSiteArray_1(String[] var0) {
      var0[0] = "println";
      var0[1] = "plus";
      var0[2] = "println";
      var0[3] = "plus";
      var0[4] = "mod";
      var0[5] = "plus";
      var0[6] = "multiply";
      var0[7] = "printValue";
      var0[8] = "printValue";
      var0[9] = "getValue";
      var0[10] = "printValue";
      var0[11] = "getValue";
      var0[12] = "printValue";
   }

   // $FF: synthetic method
   private static CallSiteArray $createCallSiteArray() {
      String[] var0 = new String[13];
      $createCallSiteArray_1(var0);
      return new CallSiteArray($get$$class$E02$DynamicTypingMain(), var0);
   }

   // $FF: synthetic method
   private static CallSite[] $getCallSiteArray() {
      CallSiteArray var0;
      if($callSiteArray == null || (var0 = (CallSiteArray)$callSiteArray.get()) == null) {
         var0 = $createCallSiteArray();
         $callSiteArray = new SoftReference(var0);
      }

      return var0.array;
   }

   // $FF: synthetic method
   private static Class $get$$class$E02$DynamicTypingMain() {
      Class var10000 = $class$E02$DynamicTypingMain;
      if($class$E02$DynamicTypingMain == null) {
         var10000 = $class$E02$DynamicTypingMain = class$("E02.DynamicTypingMain");
      }

      return var10000;
   }

   // $FF: synthetic method
   private static Class $get$$class$java$lang$String() {
      Class var10000 = $class$java$lang$String;
      if($class$java$lang$String == null) {
         var10000 = $class$java$lang$String = class$("java.lang.String");
      }

      return var10000;
   }

   // $FF: synthetic method
   static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }
}
