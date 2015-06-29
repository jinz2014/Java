import java.util.Arrays;
public class ArrayEqualsApp {
  public static void main (String[] args) {
    //--------------------------------------------
    float[] fa1 = {1.0f, 3.5f, 4.1f};
    float[] fa2 = {1.0f, 3.5f, 4.1f};
    // true
    System.out.println(Arrays.equals(fa1,fa2));

    //--------------------------------------------
    int[] ia1 = {1, 2, 4};
    int[] ia2 = {2, 2, 4};
    // false
    System.out.println(Arrays.equals(ia1,ia2));

    //--------------------------------------------
    Integer[] ia3 = new Integer[3];
    Integer[] ia4 = new Integer[3];
    for (int i = 0; i < 3; i++) {
      ia3[i] = i+1;
      ia4[i] = i+1;
    }
    // true
    System.out.println(Arrays.equals(ia3,ia4));


    //--------------------------------------------
    Point[] p1 = new Point[3];
    for (int i = 0; i < 3; i++) {
      p1[i] = new Point(i, i+1);
    }
    Point[] p2 = new Point[3];
    for (int i = 0; i < 3; i++) {
      p2[i] = new Point(i, i+1);
    }
    // false
    System.out.println(Arrays.equals(p1,p2));
    // false
    System.out.println(Arrays.deepEquals(p1,p2));

    //--------------------------------------------
    Point pa = new Point(0,1);
    Point pb = new Point(0,2);
    Point pc = new Point(0,1);
    Point [] parr1 = {pa, pb};
    Point [] parr2 = {pc, pb};
    // false
    System.out.println(Arrays.equals(parr1,parr2));
    // false
    System.out.println(Arrays.deepEquals(parr1,parr2));

    //--------------------------------------------
    String[] firstArray  = {"a", "b", "c"};
    String[] secondArray = {"a", "b", "c"};
    // true
    System.out.println(Arrays.equals(firstArray, secondArray) );
    // true
    System.out.println(Arrays.deepEquals(firstArray, secondArray) );
  }
}

class Point {
  private int x, y;
  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

