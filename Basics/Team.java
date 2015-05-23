import java.util.*;

/*
   When TreeSet is replaced by HashSet, the compile reports error:
 
   incompatible types: cannot infer type arguments for HashSet<>
     SortedSet<Player> team = new HashSet<>();
                                             ^
     reason: no instance(s) of type variable(s) E exist so that HashSet<E> conforms to SortedSet<Player>
     where E is a type-variable:
     E extends Object declared in class HashSet
 */
public class Team {

  public static void main(String[] args) {

    // SortedSet is an interface
    //SortedSet<Player> team = new TreeSet<>();
    Set<Player> team = new TreeSet<>();

    // Age sorted team 
    team.add(new Player("John", 21)); // static variable this
    team.add(new Player("Jack", 18));
    team.add(new Player("Joe",  20));
    team.add(new Player("Bill", 19));

    // list an age sorted team
    show(team);

    // public interface Comparator<T>() has methods
    // int  compare(T o1, T o2) //   Compares its two arguments for order.
    //SortedSet<Player> team1 = new TreeSet<>(new Comparator<Player>() {
    //
    // HashSet doesn't implement Comparator interface 
    //
    Set<Player> team1 = new TreeSet<>(new Comparator<Player>() {
      // implement the compareTo method
      public int compare(Player a, Player b) {
        return a.getName().compareTo(b.getName());
      }
    });
    team1.addAll(team);

    // list a name sorted team
    show(team1);
  }

  private static void show(Set s) {
    Iterator iter = s.iterator();
    while (iter.hasNext()) {
      Player p = (Player) iter.next(); // Object cannot be converted to Player
      System.out.println(p);
    }
  }



  /*
   * static variable this
   */
  private static class Player implements Comparable<Player> {
    private String name;
    private int age;

    public Player(String name, int age) {
      this.name = name;
      this.age  = age;
    }

    public int getAge() {
      return age;
    }

    public String getName() {
      return name;
    }

    // implement the compareTo method of the iterface
    // Comparable
    public int compareTo(Player other) {
      return age - other.getAge();
    }

    @Override
    public String toString() {
      return name + " - Age:"  + age;
    }
  }
}
