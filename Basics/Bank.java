import java.util.concurrent.*;

/*
   Semaphore 
 */
public class Bank {
  private final static int COUNT = 10;
  private final static Semaphore lock = 
    new Semaphore(2, true);  // true: fifo order

  public static void main(String[] args) {
    for (int i = 0; i < COUNT; i++) {
      new Thread(new Customer(lock)).start();
    }
  }
}

class Customer implements Runnable {
  private Semaphore s;
  public Customer(Semaphore s) {
    this.s = s;
  }
  public void run() {
    try {
      s.acquire(1); // one permit for each lock
      Teller.serve();
    } catch (InterruptedException e) {
      System.out.println(e.getMessage()); 
    } finally {
      s.release();
    }
  }
}

class Teller {
  public static void serve() throws InterruptedException {
    System.out.println("ATM " + Thread.currentThread().getName());
    Thread.sleep((int)(Math.random() * 10));
  }
}

