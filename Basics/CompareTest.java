import java.util.*;

/**
  * User may implement multiple custom Comparators for 
  * the same class under comparison 
  *
  * The class can also implement the Comparable interface
  * by overriding the compareTo and equals method to support
  * custom comparison
  *
  * The example shows the ways of implementing Comparators
  * and Comparable class. Note TreeSet is a sorted set in that
  * the elements added to the set are automatically sorted 
  * based on the Comparator or the Comparable class. 
  * 
  */

public class CompareTest {
  public static void main(String[] args) {
    /**
      * Comparable i/f with TreeSet (a sorted set!)
      */
    Set<MyComparableClass> r = new TreeSet<>();
    r.add(new MyComparableClass(4));
    r.add(new MyComparableClass(1));
    r.add(new MyComparableClass(3));

    // print 1, 3, 4
    System.out.print("TreeSet: ");
    System.out.println(Arrays.toString(r.toArray()));

    /**
      * Comparator with TreeSet
      */
    Set<MyClass> s = new TreeSet<>(new MyComparator());
    s.add(new MyClass(4));
    s.add(new MyClass(1));
    s.add(new MyClass(3));

    // print 1, 3, 4
    System.out.print("TreeSet: ");
    System.out.println(Arrays.toString(s.toArray()));

    /**
      * Comparable i/f with ArrayList and sort with Collections.sort
      */
    List<MyComparableClass> a = new ArrayList<>();
    a.add(new MyComparableClass(4));
    a.add(new MyComparableClass(1));
    a.add(new MyComparableClass(3));
    Collections.sort(a);

    System.out.print("ArrayList: ");
    System.out.println(Arrays.toString(a.toArray()));

    /**
      * Comparator with TreeSet and sort with Collections.sort
      */
    List<MyClass> b = new ArrayList<>();
    b.add(new MyClass(4));
    b.add(new MyClass(1));
    b.add(new MyClass(3));
    Collections.sort(b, new MyComparator());

    System.out.print("ArrayList: ");
    System.out.println(Arrays.toString(b.toArray()));
  }
}

class MyComparableClass implements Comparable {
  private int num;

  public MyComparableClass(int num) {
    this.num = num;
  }

  public String toString() {
    return String.valueOf(num);
  }

  /* 
     Compares this object with the specified object for order.
     Returns a negative integer, zero, or a positive integer as
     this object is less than, equal to, or greater than the
     specified object.
    */

  @Override
  public int compareTo(Object o) {
    if (!(o instanceof MyComparableClass))
      throw new ClassCastException();
    MyComparableClass t = (MyComparableClass)o;
    if (num < t.num)
      return -1;
    if (num > t.num)
      return 1;
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof MyComparableClass))
      return false;
    MyComparableClass t = (MyComparableClass)o;
    if (num == t.num)
      return true;
    else
      return false;
  }
}


/**
  * Comparator
  */
class MyComparator implements Comparator<MyClass> {
  @Override
  public int compare(MyClass o1, MyClass o2) {
    if (!(o1 instanceof MyClass || o2 instanceof MyClass)) 
      throw new ClassCastException();
    if (o1.getNum() < o2.getNum())
      return -1;
    else if (o1.getNum() > o2.getNum())
      return 1;
    else
      return 0;
  }
}

class MyClass {
  private int num;

  public MyClass(int num) {
    this.num = num;
  }

  public int getNum() {
    return num;
  }

  public String toString() {
    return String.valueOf(num);
  }
}

