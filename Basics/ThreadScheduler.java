import java.util.*;

public class ThreadScheduler {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      list.add((int) (Math.random() * 100));
    }

    Queue<Integer> tq = new PriorityQueue<>();
    tq.addAll(list);

    for (Integer t : tq) {
      System.out.print(t + ", ");  
    }

    System.out.println("\nDeploying threads ...");  
    while(!tq.isEmpty()) {
      System.out.print(tq.remove() + ", ");  
    }
  }
}
