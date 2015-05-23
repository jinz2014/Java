/*
  There are two buckets of balls and two threads.
  Each thread moves a few balls from one bucket to
  another bucket
 */ 
import java.util.concurrent.locks.ReentrantLock;

public class LockBucketBallGame {
  private int[] bucket = {10000, 10000};
  ReentrantLock lock = new ReentrantLock();

  public static void main(String[] args) {
    new LockBucketBallGame().doTransfer();
  }

  // must perform the ball removal and ball insertion atomically
  private void doTransfer() {
    for (int i = 0; i < 10; i++) {
      new Thread(new TransferThread(true)).start();
      new Thread(new TransferThread(false)).start();
    }
  }

  // add synchronized keyword
  public void transfer(boolean direction, int num) {
    lock.lock();
    if (direction == true) {
      bucket[0] += num;
      bucket[1] -= num;
    } else {
      bucket[0] -= num;
      bucket[1] += num;
    }
    System.out.println("Total " + (bucket[0]+bucket[1]));
    lock.unlock();
  }

  private class TransferThread implements Runnable {
    private boolean direction;
    public TransferThread(boolean direction) {
     this.direction = direction; 
    }

    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        transfer(direction, (int) (Math.random()*2000));
        try {
          Thread.sleep((int)(Math.random()*100));
        } catch (InterruptedException e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }
}




