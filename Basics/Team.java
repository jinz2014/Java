import java.util.*;

/*
   When TreeSet is replaced by HashSet, the compile reports error:
 
   incompatible types: cannot infer type arguments for HashSet<>
     SortedSet<Player> team = new HashSet<>();

    Correct: Set<Player> team = new HashSet<>();
 */
public class Team {

  public static void main(String[] args) {

    // Age sorted 
    Set<Player> team1 = new TreeSet<>();
    addPlayer(team1);
    showTeam(team1);


    // Set is not sorted!
    Set<Player> team2 = new HashSet<>();
    addPlayer(team2);
    showTeam(team2);

    /* 
       The order is determined by the compareTo method in the class Player 
    
       public interface Comparator<T>() has methods
       int  compare(T o1, T o2) 
      
       HashSet doesn't implement Comparator interface 
      
       TreeSet(Comparator<? super E> comparator)
      
       construct a team set sorted according the specified comparator
     */
    Set<Player> team3 = new TreeSet<>(
        new Comparator<Player>() {
          public int compare(Player a, Player b) {
            // implement the compareTo method
            return a.getName().compareTo(b.getName());
          }
        });
    team3.addAll(team2);

    // list a name sorted team
    showTeam(team3);
  }

  // nonstatic method cannot be referenced in main
  private static void showTeam(Set s) {
    Iterator iter = s.iterator();
    while (iter.hasNext()) {
      Player p = (Player) iter.next(); // Object cannot be converted to Player
      System.out.println(p);
    }
    System.out.println();
  }

  private static void addPlayer(Set<Player> team) {
    team.add(new Player("Ken", 21)); // static variable this
    team.add(new Player("Tom", 18));
    team.add(new Player("Joe",  20));
    team.add(new Player("Bill", 19));
  }

  /*
   * Inner class 
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

    // implement the compareTo method of the iterface Comparable
    public int compareTo(Player other) {
      return age - other.getAge();
    }

    @Override
    public String toString() {
      return "Player: " + name + " - Age:"  + age;
    }
  }
}
