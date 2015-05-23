import java.util.*;

public class BoxDemo {

  // Type parameter for method
  public static <T> void addBox(List<Box<T>> boxes, T t) {
    Box<T> box = new Box<>();
    box.set(t);
    boxes.add(box);
  }

  public static <T> void outputBoxes(List<Box<T>> boxes) {
    int counter = 0;
    for (Box<T> box: boxes) {
      T boxContents = box.get();
      System.out.println(
        "Box #" + counter + " contains [" + boxContents.toString() + "]");
      counter++;
    }
  }

  public static void main(String[] args) {
    // diamond<> infers the type Box<Integer>
    ArrayList<Box<Integer>> listOfIntegerBoxes = new ArrayList<>();

    //BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
    BoxDemo.addBox(listOfIntegerBoxes, Integer.valueOf(10));
    BoxDemo.addBox(listOfIntegerBoxes, Integer.valueOf(20));
    BoxDemo.addBox(listOfIntegerBoxes, Integer.valueOf(30));
    BoxDemo.outputBoxes(listOfIntegerBoxes);
  }
}
