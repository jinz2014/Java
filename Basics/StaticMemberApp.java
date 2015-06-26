class StaticMemberApp{
  private static int i;
  private int j;

  public static void staticMethod() {
    System.out.println("The static method in super"); 
  }
  public void nonStaticMethod() {
    System.out.println("The instance method in super"); 
  }
}

