import java.util.concurrent.*;

/* 
   Compare two parallel sum operations using Fork/Join with
   the serial implementation.

   intel-i3 four-core
   The serial implementation takes shortest time when N is 10,000,000
   The third implementation takes shortest time when N is 100,000,000
   The second implementation always takes longest time 
 */

public class ForkJoinApp {
  private final static int N = 100000000;
  public static void main(String[] args) {
    // simple example
    int fortyThree = Globals.pool.invoke(new Incrementor(42));
    System.out.println("fortyThree is " + fortyThree);

    // useful example
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = (int)(Math.random() * 100);
    }

    long sum = 0;
    long start = System.nanoTime();
    for (int i = 0; i < N; i++) {
      sum += array[i] * array[i];
    }
    long elap = System.nanoTime() - start;
    System.out.println("Serial:     Elapsed time is " + elap);

    start = System.nanoTime();
    long sump = Sum.sumArray(array);
    elap = System.nanoTime() - start;
    System.out.println("parallel a: Elapsed time is " + elap);

    if (sump != sum) 
      System.out.println("[Error] parallel sum != serial sum");
    else
      System.out.println("parallel sum finished successfully");

    start = System.nanoTime();
    sump = Applyer.sumOfSquares(array);
    elap = System.nanoTime() - start;
    System.out.println("parallel b: Elapsed time is " + elap);

    if (sump != sum) 
      System.out.println("[Error] parallel sum != serial sum");
    else
      System.out.println("parallel sum finished successfully");
  }
}

class Globals {
  public static ForkJoinPool pool = new ForkJoinPool();
}

// define the simple class
class Incrementor extends RecursiveTask<Integer> {
  int theNumber;
  Incrementor(int x) {
    theNumber = x;
  }

  @Override
  public Integer compute() {
    return theNumber + 1;
  }
}

// A Java doc example 
class Applyer extends RecursiveAction {
  final int[] array;
  final int lo, hi;
  long result; // RecursiveAction is resultless
  Applyer next; // keeps track of right-hand-side tasks

  Applyer(int[] array, int lo, int hi, Applyer next) {
    this.array = array; this.lo = lo; this.hi = hi;
    this.next = next;
  }

  long atLeaf(int l, int h) {
    long sum = 0;
    for (int i = l; i < h; ++i) // perform leftmost base step
      sum += array[i] * array[i];
    return sum;
  }

  protected void compute() {
    int l = lo;
    int h = hi;
    Applyer right = null;
    while (h - l > 1 && getSurplusQueuedTaskCount() <= 3) {
      int mid = l + (h - l) / 2;
      right = new Applyer(array, mid, h, right);
      right.fork();
      h = mid;
    }
    long sum = atLeaf(l, h);
    while (right != null) {
      if (right.tryUnfork()) // directly calculate if not stolen
        sum += right.atLeaf(right.lo, right.hi);
      else {
        right.join();
        sum += right.result;
      }
      right = right.next;
    }
    result = sum;
  }

  static long sumOfSquares(int[] array) {
    Applyer a = new Applyer(array, 0, array.length, null);
    Globals.pool.invoke(a);
    return a.result;
   }
}

/*
  Sum all the elements of an array using
  fork/join parallel programming model
 */
class Sum extends RecursiveTask<Long> {
  static final int TD = 50;
  int low;
  int high;
  int[] array;

  Sum(int[] arr, int lo, int hi) {
    array = arr;
    low   = lo;
    high  = hi;
  }

  protected Long compute() {
    // avoid creating too many Sum objects which
    // may decrease the efficiency of parallelism
    if (high - low <= TD) {
      long sum = 0;
      for (int i = low; i < high; i++) {
        sum += array[i] * array[i];
      }
      return sum;
    } else {

      int mid = low + (high - low) / 2;
      Sum left  = new Sum(array, low, mid);
      Sum right = new Sum(array, mid, high);

      // fork off a thread for left sum
      // The function returns quickly
      left.fork(); 
      long rightAns = right.compute();

      // wait for the left sum thread to finish
      long leftAns = left.join();
      return leftAns + rightAns;
    }
  }

  static long sumArray(int[] array) {
    return Globals.pool.invoke(new Sum(array, 0, array.length));
  }
}
