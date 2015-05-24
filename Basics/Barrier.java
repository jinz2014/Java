import java.util.concurrent.*;

/*
   public CyclicBarrier(int parties,
                Runnable barrierAction)
   Creates a new CyclicBarrier that will trip when the given number of parties (threads)
   are waiting upon it, and which will execute the given barrier action when the barrier
   is tripped, performed by the last thread entering the barrier.

Parameters:
parties - the number of threads that must invoke await() before the barrier is tripped
barrierAction - the command to execute when the barrier is tripped, or null if there is no action
*/

public class Barrier {

  // number of terms in the ln(1-x)
  private final static int term = 10;

  // each term element
  private static double[] arr = new double[term];

  // the value of x
  private static float x;

  public static void main(String[] args) 
    throws InterruptedException, BrokenBarrierException {
    /*
    await()

    If the current thread is the last thread to arrive, 
    and a non-null barrier action was supplied in the constructor,
    then the current thread runs the action before allowing the other
    threads to continue. 
    If an exception occurs during the barrier action then that exception
    will be propagated in the current thread and the barrier is placed
    in the broken state
    */
    for (x = -0.9f; x < 1.0f; x = x + 0.1f) { 

      // including the main thread
      CyclicBarrier barrier = new CyclicBarrier(term + 1, new Slow_Manager());

      // The Worker threads are synchronized by a barrier
      for (int i = 0; i < term; i++) {
        new Thread(new Worker(barrier, i)).start();
      }
      barrier.await();
    }
  }

  private static class Slow_Manager implements Runnable {
    public void run() {
      try {
        double sum = 0;
        for (double t : arr) {
          Thread.sleep(1000);
          sum += t;
        }
        System.out.printf("ln(1-x) = %f (x = %f)\n", -sum, x); // -0 = 0
      } catch (InterruptedException e) {
        System.out.println(e.getMessage()); 
      }
    }
  }

  // inner class
  // Each worker works on a term and updates the array value
  private static class Worker implements Runnable {
    private int index;
    private CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier, int index) {
      this.barrier = barrier;
      this.index   = index;
    }

    public void run() {
      double r = Math.pow(x, index + 1) / (index + 1);
      arr[index] = r;
      try {
        barrier.await();
      } catch (InterruptedException e) {
        System.out.println(e.getMessage()); 
      } catch (BrokenBarrierException e) {
        e.printStackTrace(); 
      }
    }
  }
}
