/*
   Difference between the daemon and non-daemon thread
   detailed information of a Thread (group)
 */

public class PrimeNumberGenerator {
  public static void main(String[] args) {
    Thread g = new Thread(new WorkerThread());
    System.out.printf("thread name (%s)\n", g.getName());

    // if it is false, the WorkerThread runs forever
    // unless interrupted
    g.setDaemon(true); 

    // 1 active thread
    System.out.printf("thread group (%s), active thread(s) = %d\n", 
        g.getThreadGroup().getName(), Thread.activeCount());

    // start the thread
    g.start();

    // 2 active threads
    System.out.printf("thread group (%s), active thread(s) = %d\n", 
        g.getThreadGroup().getName(), Thread.activeCount());

    // Details of all active threads
    Thread[] threads = new Thread[Thread.activeCount()];
    Thread.enumerate(threads);
    for (Thread t : threads) {
      System.out.printf("thread name (%s), priority = %d\n", 
          t.getName(), t.getPriority());
    }

    // main thread sleeps for 10 ms. If there is no sleep,
    // then the (Daemon) WorkerThread has no time to execute
    try {
      Thread.sleep(10); 
    } catch (InterruptedException e) {
    }
  }
}

class WorkerThread implements Runnable {
  public void run() {
    long i = 1;
    long j;
    while (true) {
      for (j = 2; j < i; j++) {
        long n = i % j;
        if (n == 0) {
          break;
        }
      }
      if (i == j) {
        System.out.print(" " + i);
      }
      i++;
    }
  }
}
