import java.util.*;

public class SwapTest {

  // Generic method
  public static <T> void swap(List<T> list, int fromIndex, int toIndex) {
    // return the value of list element to be overwritten
    T t = list.set(fromIndex, list.get(toIndex));
    list.set(toIndex, t);
  }

  // Generic method
  public static <T> void swap (T a, T b) {
    T t = a;
    a = b;
    b = t;
  }

  public static void main(String args[]) {
    Integer a = 5, b = 6;

    // pass by value
    swap(a,b);

    // so a and b are not changed
    System.out.println(a + " " + b);

    // However, we can apply swap on a list
    List<Double> num = new ArrayList<>();

    for (int i = 0; i < 5; i++)
      num.add(i/2.0);

    for (Double n : num) {
      System.out.print(n + " ");
    }
    System.out.println();

    for (int i = 0; i < 5/2; i++)
      swap(num, i, 4-i); // Type inference

    for (Double n : num) {
      System.out.print(n + " ");
    }
    System.out.println();


  }
}

