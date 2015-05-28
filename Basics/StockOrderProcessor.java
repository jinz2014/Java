
import java.util.*;
import java.util.concurrent.*;

public class StockOrderProcessor {

  public final static int NUM_OF_ORDERS = 10000;
  
  private static class OrderExecutor implements Callable<Integer> {
    private int id = 0;
    private int count = 0;

    // ctor
    public OrderExecutor(int id) {
      this.id = id;
    }

    // public interface Callable<V> {
    //   V call() throws Exception
    // }
    public Integer call() throws Exception {
        while (count < 50) {
          count++;
          Thread.sleep(new Random(System.currentTimeMillis()%100).nextInt(10));
        }
        System.out.println("Execute order successfully: " + id);
      return id;
    }
  }

  /*
  private static void SubmitOrder(int id) {
    Callable<Integer> callable = new OrderExecutor(id);
    ordersToProcess.add(executor.submit(callable));
  }
  */

  static class CancelThread implements Runnable {
    private List<Future> ordersToProcess;

    public CancelThread(List<Future> ordersToProcess) {
      this.ordersToProcess = ordersToProcess;
    }

    public void run() {
      Random rnd = new Random(System.currentTimeMillis() % 100);

      for (int i = 0; i < 100; i++) {
        // thread number to cancel
        int id = rnd.nextInt(StockOrderProcessor.NUM_OF_ORDERS);

        // check cancel status
        boolean status = ordersToProcess.get(id).cancel(true);
        if (status)
          System.out.println("Cancel order succeeded: " + id);
        else
          System.out.println("Cancel order failed: " + id);

        try {
          Thread.sleep(rnd.nextInt(100));
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    List<Future> ordersToProcess = new ArrayList<>();

    // submit work
    for (int i = 0; i < NUM_OF_ORDERS; i++) {
      Callable<Integer> callable = new OrderExecutor(i);
      ordersToProcess.add(executor.submit(callable));
    }

    new Thread(new CancelThread(ordersToProcess)).start();
    try {
      // allow time to complete all the pending orders to 
      // prevent the main thread from terminating the pending requests
      executor.awaitTermination(30, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // check staus
    int count = 0;
    for (Future f : ordersToProcess) {
      if (f.isCancelled()) {
        count++;
      }
    }
    System.out.printf("Cancelled %d trades\n", count);
    executor.shutdown();
  }
}

