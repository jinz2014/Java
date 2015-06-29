/** 
 * Local class Example
 */

class LocalClass {

  private int a = 20;

  public void method (int b) {
    // define local(inner) class
    class Inner {
      int c = 30;
      public void innerMethod() {
        // 10
        System.out.println("formal parameter b = " + b);
        // 20 (can access the private member of the 
        // enclosing class
        System.out.println("outer class variable a = " + a);
        // 30
        System.out.println("inner class variable c = " + c);
      }
    }
    new Inner().innerMethod();
  }

  public static void main(String[] args) {
    Outer t = new Outer();
    t.method(10);
  }
}
