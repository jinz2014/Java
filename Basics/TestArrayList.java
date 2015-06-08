import java.util.*;

/**
  * public Object[] toArray()
  * return an array containing all the elements in the ArrayList
  * object from first to last element
  *
  *
  * Arrays.toString(Object[] a)
  * return a String representation of the contents of the array
  *
  */

public class TestArrayList {
  public static void main(String args[]) {

    // 10 is the capacity of the list
    ArrayList<Integer> a = new ArrayList<>(10);

    // empty list so the size is 0
    System.out.println(a.size());

    a.add(5);

    // index out of bounds
    //a.add(2, 4);

    a.add(1, 4);

    // 2
    System.out.println(a.size());

    //[5,4]
    System.out.println(Arrays.toString(a.toArray()));

    // 5,6,4
    a.add(1, 6);

    // remove 5, 6 from the list
    //a.subList(0,2).clear();
    List<Integer> b = a.subList(0,2);
    //b.clear();
    System.out.println(Arrays.toString(b.toArray()));
    b.clear();

    a.add(0, 8);
    a.add(1, 9);

    // 8,9,4
    System.out.println(Arrays.toString(a.toArray()));

  }
}
