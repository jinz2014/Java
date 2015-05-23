/** 
 * Local class Example
 */

class Outer {

  private int a = 20;

  public void method (int b) {
    // local class
    class Inner {
      int c = 30;
      public void innerMethod() {
        System.out.println("formal parameter b = " + b);
        System.out.println("outer class variable a = " + a);
        System.out.println("inner class variable c = " + c);
      }
    }
    new Inner().innerMethod();
  }

  public static void main(String[] args) {
    Outer a = new Outer();
    a.method(10);
  }
}
