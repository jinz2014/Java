public class Producer_Consumer {
  public static void main (String[] args) {
    Mailbox b = new Mailbox();
    new Thread(new Consumer(b)).start();
    new Thread(new Producer(b)).start();
  }
}

class Consumer implements Runnable {
  private Mailbox b;
  public Consumer(Mailbox b) {
    this.b = b;
  }
  public void run() {
    for (int i = 0; i < 10; i++) {
      b.get();
    }
  }
}

class Producer implements Runnable {
  private Mailbox b;
  public Producer(Mailbox b) {
    this.b = b;
  }
  public void run() {
    for (int i = 0; i < 10; i++) {
      b.put((int)(Math.random() * 100));
    }
  }
}

class Mailbox {
  private int balls;
  private boolean avail = false;

  public synchronized int get() {
    if (avail == false) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("get(): " + e.getMessage());
      }
    }
    System.out.println("Get " + balls + " balls.");
    avail = false;
    notify();
    return balls;
  }

  public synchronized void put(int balls) {
    if (avail == true) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("put(): " + e.getMessage());
      }
    }
    System.out.println("Put " + balls + " balls.");
    avail = true;
    this.balls = balls;
    notify();
  }
}

