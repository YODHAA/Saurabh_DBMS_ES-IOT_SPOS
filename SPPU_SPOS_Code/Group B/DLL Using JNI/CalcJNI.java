   public class CalcJNI {
   static {
      System.loadLibrary("calc"); // Load native library at runtime
                                   // calc.dll (Windows) or libcalc.so (Unix)
   }
 
   // Declare a native method add() that receives nothing and returns void
   private native int add(int n1, int n2);
   private native int sub(int n1, int n2);
   private native int mul(int n1, int n2);
   private native int div(int n1, int n2);
 
   // Test Driver
   public static void main(String[] args) {
      System.out.println("Addition is="+new CalcJNI().add(10,20));  // invoke the native method
      System.out.println("Subtraction is="+new CalcJNI().sub(10,20));
      System.out.println("Multiplication is="+new CalcJNI().mul(10,20));
      System.out.println("Division is="+new CalcJNI().div(10,20));
   }
}
