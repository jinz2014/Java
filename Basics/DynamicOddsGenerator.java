/**
 * Generate a list of odd numbers using an inner class
 *
 */
public class DynamicOddsGenerator {

  private final static int SIZE = 25;

  private int[] a = new int[SIZE];

  // inner class
  private class OddsIterator {
    private int next = 0;
    public boolean hasNext() {
      return next <= SIZE - 1;
    }

    public int getNext() {
      int r = a[next++];
      if (r % 2 == 1)
        return r;
      else
        return -1;
    }
  }

  // ctor
  public DynamicOddsGenerator() {
    for (int i = 0; i< SIZE; i++) {
      a[i] = (int)(Math.random() * SIZE);
    }
  }

  public void printOdds() {
    OddsIterator iterator = this.new OddsIterator();
    while (iterator.hasNext()) {
      int r = iterator.getNext();
      if (-1 != r) {
        System.out.print(r + " ");
      }
    }
    System.out.println();
  }

  public static void main(String[] str) {
    DynamicOddsGenerator g = new DynamicOddsGenerator();
    g.printOdds();
  }
}
