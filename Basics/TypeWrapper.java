/* java
 * char -> Character
 * int  -> Integer
 * float -> Float
 *
 * the object of the wrapper class is immutable ??
 *
 */

public class TypeWrapper {
  public static void main (String[] args) {
    System.out.println("Minimum Integer value: " + Integer.MIN_VALUE);
    System.out.println("Maxmum Integer value: " + Integer.MAX_VALUE);

    Integer n1 = new Integer(5);
    Integer n2 = new Integer("10");
    System.out.println("n1 holds value " + n1.intValue());
    System.out.println("n2 holds value " + n2.intValue());

    // false
    System.out.println("n1.equals(n2) = " + n1.equals(n2));
    // -1 (5 < 10)
    System.out.println("n1.compareTo(n2) = " + n1.compareTo(n2));
    // 1 (10 > 5)
    System.out.println("n2.compareTo(n1) = " + n2.compareTo(n1));

    n2 = new Integer("5");
    // true
    System.out.println("n1.equals(n2) = " + n1.equals(n2));
    // 0
    System.out.println("n1.compareTo(n2) = " + n1.compareTo(n2));
    System.out.println("n2.compareTo(n1) = " + n2.compareTo(n1));

    System.out.println(Integer.parseInt("245"));
    System.out.println(Integer.parseInt("FF", 16)); // hex
    System.out.println(Integer.parseInt("Jim", 27)); // hex
  }
}
