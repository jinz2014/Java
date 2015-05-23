class StaticMemberApp{
  private static int i;
  private int j;

  public static void staticMethod() {
    System.out.println("The static method in super"); 
  }
  public void nonStaticMethod() {
    System.out.println("The instance method in super"); 
  }

  /*
  public static void main(String[] args) {
    i = 5;
    //j = 10; // error
    staticMethod();
    //nonStaticMethod(); // error
  }
  */
}


// a static method cannot be overriden
class Subclass extends StaticMemberApp{
  public static void staticMethod() {
    System.out.println("The static method in subclass"); 
  } // error

  @Override
  public void nonStaticMethod() {
    System.out.println("The instance method in subclass"); 
  }

  public static void main(String[] args) {
    Subclass a = new Subclass();
    StaticMemberApp b = a;

    // Classname.static_method_name
    StaticMemberApp.staticMethod();
    Subclass.staticMethod();

    b.staticMethod(); // the static method in super

    a.staticMethod(); // the static method in subclass

    // polym
    b.nonStaticMethod(); // the instance method in subclass
  }
}
