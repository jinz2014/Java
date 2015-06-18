/**
 * Generic class for finding a max in an array
 *
 */
import java.util.Arrays;

public class MaxTest<T extends Comparable> {
  public int maxIndexOf(T[] a) {
    if (a.length < 1) 
      return -1;

    int pos = 0;

    for (int i = 1; i < a.length; i++) {
      if (a[i].compareTo(a[pos]) > 0)
        pos = i;
    }
    return pos;
  }

  public static void main(String args[]) {
    //Float[] a = {1,2, 4, 7, 3, 1}; // error
    Float[] a = {1.0f, 2.0f, 4.0f, 7.0f, 3.0f, 1.0f}; // error
    MaxTest<Float> r1 = new MaxTest<>();
    System.out.println(Arrays.toString(a));
    System.out.println("index of max number: " + r1.maxIndexOf(a));

    Integer[] b = {1,2, 3, 4, 7, 1};
    MaxTest<Integer> r2 = new MaxTest<>();
    System.out.println(Arrays.toString(b));
    System.out.println("index of max number: " + r2.maxIndexOf(b));
  }
}
