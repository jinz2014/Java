/**
  * 
  * values return an array of enumeration constants.
  * When printing the element d, its toString method
  * is called
  * 
  */

enum Day { // enum is a class
  MON,TUE,WED,THU,FRI,SAT,SUN;

  @Override
  public String toString() {
    String s = super.toString();
    return s.substring(0,1) + s.substring(1).toLowerCase();
  }
};

public class WeekdayList {
  public static void main(String[] args) {
    //--------------------------------------------
    // all the constants of an enum type can be 
    // obtained by calling the values() method
    //--------------------------------------------
    for (Day d : Day.values()) {
      System.out.print(d + " ");
    }
    System.out.println();

    //--------------------------------------------
    // convert a String representation of an enum
    // into an enum type
    //
    // public static T valueOf(String)
    //--------------------------------------------

    Day monday = Day.valueOf("MON");  // or args[0]
    System.out.println(monday);

    //--------------------------------------------
    // compare
    //--------------------------------------------
    Day mon    = Day.valueOf("MON");
    Day tuesday = Day.valueOf("TUE");
    if (monday == tuesday) // false
      System.out.println("monday and tuesday are the same day");

    if (mon == monday)  // true
      System.out.println("mon and monday are the same day");

    //--------------------------------------------
    // convert a enum type to its (default/customized)
    // String representation
    //--------------------------------------------
      System.out.println(monday.toString());
  }
}
