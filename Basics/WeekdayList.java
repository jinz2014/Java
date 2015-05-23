/**
  * enum is a class!
  * 
  * values return an array of enumeration constants.
  * When printing the element d, its toString method
  * is called
  * 
  */

enum Day {
  MON,TUE,WED,THU,FRI,SAT,SUN;

  @Override
  public String toString() {
    String s = super.toString();
    return s.substring(0,1) + s.substring(1).toLowerCase();
  }
};

public class WeekdayList {
  public static void main(String[] args) {
    for (Day d : Day.values()) {
      System.out.print(d + " ");
    }
    System.out.println();
  }
}
