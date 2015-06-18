import java.util.*;
import java.util.concurrent.*;

/*
   In wait-notify case, a shared channel is used for write and read
   In exchange case, both buffers are empty. The producer puts data
   in the buffer. The consumer keeps waiting for the data to be ready.
   When producer buffer is full, the two buffers are exchanged.

   The program doesn't use explicit synchronized construct.
 */


public class ProductExchanger {
  public static Exchanger<List<Integer>> exchanger = new Exchanger<>();
  public static void main (String[] args) {
    Thread producer = new Thread(new Producer());
    Thread consumer = new Thread(new Consumer());
    producer.start();
    consumer.start();
    try {
      while (System.in.read() != '\n') {}
    } catch (Exception e) {
      e.printStackTrace();
    }
    producer.interrupt();
    consumer.interrupt();
  }
}

class Producer implements Runnable {
  private List<Integer> buffer =  new ArrayList<>();
  private boolean ok = true;
  private final int SIZE = 10;

  public void run() {
    while (ok) {
      if (buffer.isEmpty()) {
        try {
          // fill the buffer
          for (int i = 0; i < SIZE; i++) {
            buffer.add((int)(Math.random()*100));
          }
          // simulate the time spent on producing
          // and filling the buffer
          Thread.sleep((int)(Math.random()*1000));

          System.out.print("Producer buffer: ");
          for (int i : buffer) {
            System.out.print(i+", ");
          }
          System.out.println();
          buffer = ProductExchanger.exchanger.exchange(buffer);
        } catch (InterruptedException e) {
          ok = false;
        }
      }
    }
  }
}


class Consumer implements Runnable {
  private List<Integer> buffer =  new ArrayList<>();
  private boolean ok = true;

  public void run() {
    while (ok) {
      if (buffer.isEmpty()) {
        try {
          buffer = ProductExchanger.exchanger.exchange(buffer);
          System.out.print("Consumer buffer: ");
          for (int i : buffer) {
            System.out.print(i+", ");
          }
          System.out.println();

          // simulate the time spent on consuming the buffer data
          Thread.sleep((int)(Math.random()*1000));

          System.out.print("Producer buffer: ");
          for (int i : buffer) {
            System.out.print(i+", ");
          }
          System.out.println();
          buffer = ProductExchanger.exchanger.exchange(buffer);
        } catch (InterruptedException e) {
          ok = false;
        }
      }
    }
  }
}


