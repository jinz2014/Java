import java.util.*;

public class BoxDemo1 {

  public <T> void addBox(List<Box<T>> boxes, T t) {
    Box<T> box = new Box<>();
    box.set(t);
    boxes.add(box);
  }

  public <T> void outputBoxes(List<Box<T>> boxes) {
    int counter = 0;
    for (Box<T> box: boxes) {
      T boxContents = box.get();
      System.out.println(
        "Box #" + counter + " contains [" + boxContents.toString() + "]");
      counter++;
    }
  }

  public static void main(String[] args) {
    BoxDemo1 d = new BoxDemo1();

    // diamond<> infers the type Box<Integer>
    ArrayList<Box<Integer>> Boxes = new ArrayList<>();

    d.addBox(Boxes, Integer.valueOf(10));
    d.addBox(Boxes, Integer.valueOf(20));
    d.addBox(Boxes, Integer.valueOf(30));
    d.outputBoxes(Boxes);
  }
}
