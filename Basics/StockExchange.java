import java.io.IOException;
import java.util.concurrent.*;

public class StockExchange {
  private final static int sz = 1;

  public static void main (String[] args) {
    BlockingQueue<Integer> q = 
      new LinkedBlockingQueue<Integer>();

    Seller seller = new Seller(q);
    Thread[] sellerThread = new Thread[sz]; 
    for (int i = 0; i < sz; i++) {
      sellerThread[i] = new Thread(seller);
      sellerThread[i].start();
    }

    Buyer Buyer = new Buyer(q);
    Thread[] buyerThread = new Thread[sz]; 
    for (int i = 0; i < sz; i++) {
      buyerThread[i] = new Thread(Buyer);
      buyerThread[i].start();
    }

    try {
      while (System.in.read() != '\n') {} 
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (Thread t : sellerThread) {
      t.interrupt();
    }
    for (Thread t : buyerThread) {
      t.interrupt();
    }
  }
}

class Seller implements Runnable {
  private BlockingQueue<Integer> bq;
  private boolean terminate = false;

  Seller(BlockingQueue<Integer> q) {
    bq = q;
  }

  public void run() {
    int i = 0;
    while (terminate == false) {
      i++;
      try {
        bq.put(i);
        //System.out.println(Thread.currentThread().getName());
      } catch (InterruptedException e) {
        terminate = true;
      }
    }
  }
}

class Buyer implements Runnable {
  private BlockingQueue<Integer> bq;
  private boolean terminate = false;

  Buyer(BlockingQueue<Integer> q) {
    bq = q;
  }

  public void run() {
    while (terminate == false) {
      try {
        Integer i = bq.take();
        System.out.println(Thread.currentThread().getName() + ":"
            + i);
      } catch (InterruptedException e) {
        terminate = true;
      }
    }
  }
}
