// compiler reports Warnings when missing <> after Mailbox

public class Producer_Consumer_Generic {

  public static void main (String[] args) {

    Mailbox<Integer> b = new Mailbox<>(); 
    Mailbox<String> b1 = new Mailbox<>();

    Integer[] d = new Integer[10];
    for (int i = 0; i < 10; i++) {
      d[i] = (int) (Math.random() * 100);
    }

    String[] s = new String[10];
    for (int i = 0; i < 10; i++) {
      s[i] = "ID" + d[i];
    }

    new Thread(new Consumer<Integer>(b)).start();
    new Thread(new Producer<Integer>(b, d)).start();

    new Thread(new Consumer<String>(b1)).start();
    new Thread(new Producer<String>(b1, s)).start();
  }
}

class Consumer<T> implements Runnable {
  private Mailbox<T> b;
  public Consumer(Mailbox<T> b) {
    this.b = b;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      b.get();
    }
  }
}

class Producer<T> implements Runnable {
  private Mailbox<T> b;
  private T[] d;
  public Producer(Mailbox<T> b, T[] d) {
    this.b = b;
    this.d = d;
  }
  public void run() {
    for (int i = 0; i < 10; i++) {
      b.put(d[i]);
    }
  }
}

class Mailbox<T> {
  private T message;
  private boolean avail = false;

  public synchronized T get() {
    if (avail == false) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("get(): " + e.getMessage());
      }
    }
    System.out.println("Get message: " + message);
    avail = false;
    notify();
    return message;
  }

  public synchronized void put(T message) {
    if (avail == true) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("put(): " + e.getMessage());
      }
    }
    System.out.println("Put message: " + message);
    avail = true;
    this.message = message;
    notify();
  }
}


