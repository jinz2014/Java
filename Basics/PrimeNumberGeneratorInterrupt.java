import java.io.*;

public class PrimeNumberGeneratorInterrupt {
  public static void main(String[] args) {
    Thread g = new Thread(new WorkerThread());

    g.start();

    InputStreamReader in = new InputStreamReader(System.in);
    try {
      while (in.read() != '\n') {}
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    g.interrupt();

    try {
      Thread.sleep(100); 
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }

    if (g.interrupted()) {
      System.out.println("WorkerThread has been interrupted");
    } else {
      System.out.println("WorkerThread is not running");
    }

    Thread lazy = new Thread(new LazyWorker());
    lazy.start();

    try {
      Thread.sleep(100); 
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
    lazy.interrupt();
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

      if (Thread.interrupted()) {
        return;
      }
    }
  }
}

class LazyWorker implements Runnable {
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.out.println("Lazy Worker: " + e.toString());
    }
  }
}
