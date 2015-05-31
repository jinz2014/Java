//import java.text.DateFormatSymbols;
import java.util.*;
import java.util.concurrent.*;

/*
   The Callable interface has a call method, which will be implemented
   for a specific service. To invoke the call method, Java provides
   ExecutorService to run a callable task. The ExecutorService returns
   a future object from which we can check the status and return value
   of the callable task
 */

public class AnnualSalesApp {
  public static int NUM_OF_CUSTOMERS = 100;
  public static int NUM_OF_MONTHS = 12;
  public static int[][] sales;

  // Sum of all elements in a matrix row 
  private static class RowSum implements Callable<Integer> {
    private int companyID;
    public RowSum(int companyID) {
      this.companyID = companyID;
    }

    // public interface Callable<V> {
    //   V call() throws Exception
    // }
    public Integer call() {
      int sum = 0;
      for (int c = 0; c < NUM_OF_MONTHS; c++) {
        sum += sales[companyID][c];
      }
      return sum;
    }
  }

  public static void main(String[] args) throws Exception {
    sales = new int[NUM_OF_CUSTOMERS][NUM_OF_MONTHS];
    for (int i = 0; i < NUM_OF_CUSTOMERS; i++)
      for (int j = 0; j < NUM_OF_MONTHS; j++)
        sales[i][j] = (int)(Math.random() * 100);

    ExecutorService executor = Executors.newFixedThreadPool(10);
    Set<Future<Integer>> set = new HashSet<>();
    for (int r = 0; r < NUM_OF_CUSTOMERS; r++) {
      Callable<Integer> callable = new RowSum(r);
      // Execute the Callable task and return the Future 
      Future<Integer> future = executor.submit(callable);
      set.add(future);
    }

    // The results are obtained sequentially in the loop order
    int sump = 0;
    for (Future<Integer> f : set) {
      sump += f.get();
    }

    // verificatoin
    int sum = 0;
    for (int i = 0; i < NUM_OF_CUSTOMERS; i++)
      for (int j = 0; j < NUM_OF_MONTHS; j++)
        sum += sales[i][j];

    if (sum != sump)
      System.out.printf("Total sale mismatch: %d %d\n", sum, sump);
    else
      System.out.printf("Total sale finished successfully\n");

    executor.shutdown();
  }
}
