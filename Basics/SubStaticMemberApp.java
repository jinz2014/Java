// a static method cannot be overriden with @Override.
// However, a static method can overshadow the same static method
// in the base class
class SubStaticMemberApp extends StaticMemberApp{
  @Override
  public static void staticMethod() {
    System.out.println("The static method in SubStaticMemberApp"); 
  }

  @Override
  public void nonStaticMethod() {
    System.out.println("The instance method in SubStaticMemberApp"); 
  }

  public static void main(String[] args) {
    SubStaticMemberApp d = new SubStaticMemberApp();
    StaticMemberApp b = d;

    // Classname.static_method_name
    StaticMemberApp.staticMethod();   
    SubStaticMemberApp.staticMethod(); 

    b.staticMethod(); // always the static method in super
    d.staticMethod(); // the static method in SubStaticMemberApp
    b.nonStaticMethod(); // poly - the instance method in SubStaticMemberApp
  }
}

