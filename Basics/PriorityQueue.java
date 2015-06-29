import java.util.*;

public class ThreadScheduler {
  public static void main(String[] args) {

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add((int) (Math.random() * 100));
    }

    //----------------------------------------------
    // default PriorityQueue constructor
    //----------------------------------------------
    Queue<Integer> tq = new PriorityQueue<>();
    tq.addAll(list);

    // still the random order
    for (Integer t : tq) {
      System.out.print(t + ", ");  
    }

    System.out.println("\nDeploying threads ...");  
    while(!tq.isEmpty()) {
      // remove the queue elements in natural order
      System.out.print(tq.remove() + ", ");  
    }
    System.out.println();

    //----------------------------------------------
    // PriorityQueue constructor with 
    // collection argument
    //----------------------------------------------
    Queue<Integer> tq2 = new PriorityQueue<>(list);

    // still the random order
    for (Integer t : tq2) {
      System.out.print(t + ", ");  
    }

    System.out.println("\nDeploying threads ...");  
    while(!tq2.isEmpty()) {
      // remove the queue elements in natural order
      System.out.print(tq2.remove() + ", ");  
    }
    System.out.println();


  }
}
