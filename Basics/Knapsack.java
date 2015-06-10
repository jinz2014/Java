import java.util.*;

/**
  * Reference
  *
  * Let f(i,y) be the value of an optimal solution in which
  * the remaining capacity is y and the remaining objects 
  * are i, i+1, ..., n
  *
  * f(n,y) = price[n] if y > weight[n]; otherise it is 0
  *
  * f(i,y) = max{f(i+1,y), f(i+1,y-weight[i])+price[i]} if
  * y >= weight[i]; otherwise it is f(i+1,y)
  *
  */
public class Knapsack {
  private static int[] price; 
  private static int[] weight; 
  private static int n;
  private static Map<Pair, Integer> map = new HashMap<>();

  public Knapsack (int [] price,
                   int [] weight) {
    if (price.length != weight.length) {
      System.out.println("The size of price and weight arrays should match");
      System.exit(1);
    } else {
      this.n = price.length;
      this.price = price;
      this.weight = weight;
    }
  }

  public static int max(int a, int b) {
    return a < b ? b : a;
  }

  public static int compute(int i, int y) {
    if (i == n - 1) 
      return (y < weight[n-1] ? 0 : price[n-1]);

    if (y < weight[i]) {
      System.out.printf("[1] call(%d,%d)%n", i+1, y);
      return compute(i+1, y);
    } else {
      System.out.printf("[1] call(%d,%d)%n", i+1, y);
      int f = compute(i+1, y);
      System.out.printf("[1] call(%d,%d)%n", i+1, y-weight[i]);
      int g = compute(i+1,y-weight[i]) + price[i];
      return max(f, g); 
    }
  }

  /**
    * Use Hash table to eliminate duplicate search 
    */
  public static int compute2(int i, int y) {

    if (i == n - 1) 
      return (y < weight[n-1] ? 0 : price[n-1]);

    int f;
    Pair p, q;
    if (y < weight[i]) {
      p = new Pair(i+1,y);
      if (!map.containsKey(p)) {
        System.out.printf("[2] call(%d,%d)%n", i+1, y);
        f = compute2(i+1, y);
        map.put(p, f);
      }
      return map.get(p);
    } 
    else {
      p = new Pair(i+1,y);
      q = new Pair(i+1,y-weight[i]);
      if (!map.containsKey(p)) {
        System.out.printf("[2] call(%d,%d)%n", i+1, y);
        f = compute2(i+1, y);
        map.put(p, f);
      }
      if (!map.containsKey(q)) {
        System.out.printf("[2] call(%d,%d)%n", i+1, y-weight[i]);
        f = compute2(i+1, y-weight[i]) + price[i];
        map.put(q, f);
      }
      return max(map.get(p), map.get(q));
    }
  }

  public static void main(String[] args) {
    int[] price  = {6,3,5,4,6};
    int[] weight = {2,2,6,5,4};
    Knapsack k = new Knapsack(price, weight);
    System.out.println("The max value (capacity = 10) is " + k.compute(0, 10));
    System.out.println("The max value (capacity = 10) is " + k.compute2(0, 10));
  }
}

// Override hashCode() and equals
// See TestObjectKeyHashMap.java 
class Pair {
  private int i;
  private int y;
  public Pair(int i, int y) {
    this.i = i;
    this.y = y;
  }
  public int hashCode() {
    int hash = 31 + i;
    hash += hash * 31 + y;
    return hash;
  }
  public boolean equals(Object o) {
    if (this == o)
      return false;
    if (o instanceof Pair) {
      Pair p = (Pair) o;
      return (i == p.i && y == p.y);
    } else {
      return false;
    }
  }
}
