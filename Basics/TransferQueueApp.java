import java.util.*;
import java.util.concurrent.*;

/*
   case 1: Producer speed > consumer speed
   case 2: Producer speed < consumer speed
 */
public class TransferQueueApp { 
  public static void main (String[] args) throws InterruptedException {
    Thread[] c = new Thread[10];
    boolean alive = true;
    int i;
    TransferQueue<String> q = new LinkedTransferQueue<>();
    Thread p = new Thread(new Producer(q));
    p.setDaemon(true);
    p.start();

    for (i = 0; i < 10; i++) {
      c[i] = new Thread(new Consumer(q));
      c[i].setDaemon(true);
      c[i].start();
      try {
        // Consumer arrival time
        Thread.sleep((int)(Math.random()*1000));
      } catch (InterruptedException e) {
        System.out.println(e.getMessage());
      }
    }

    while (alive) {
      for (i = 0; i < 10; i++) {
        if (c[i].isAlive()) {
          alive = true;
          break;
        }
      }
      if (i == 10)
        break;
    }
  }
}


/* 
   hasWaitingConsumer() returns true if there is at least one consumer 
   waiting to receive an element via BlockingQueue.take() or timed poll.

   transfer(E e)
    Transfers the specified element immediately if there exists a consumer 
    already waiting to receive it (in BlockingQueue.take() or timed poll),
    else waits until the element is received by a consumer.
 */

class Producer implements Runnable {
  private TransferQueue<String> queue;

  public Producer(TransferQueue<String> queue) {
    this.queue = queue;
  }

  private String produce() {
    return "ID: " + (new Random().nextInt(100)); 
  }

  public void run() {
    try {
      while (true) {
        if (queue.hasWaitingConsumer()) {
          queue.transfer(produce()); // transfer a String element
        }
        System.out.printf("There are about %d consumers waiting to receive service.\n", 
            queue.getWaitingConsumerCount());

        // Producer processing time 
        //TimeUnit.SECONDS.sleep((int)(Math.random()*10));
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      System.out.println(e.getMessage()); }
  }
}

/*
  E take() throws InterruptedException

  Retrieves and removes the head of this queue,
  waiting if necessary until an element becomes available.

  Returns:
  the head of this queue
 */
class Consumer implements Runnable {
  private TransferQueue<String> queue;

  public Consumer(TransferQueue<String> queue) {
    this.queue = queue;
  }

  public void run() {
    try {
      System.out.println("Consumer " +
          Thread.currentThread().getName() + " " + queue.take());
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
