import java.util.Arrays;

public class ArrayCopyApp {
  public static void main (String[] args) {
    float[] floatArray = {1.0f, 3.5f, 4.1f};
    float[] floatArrayCopy = floatArray.clone();
    System.out.println(Arrays.toString(floatArray) + " - Original");
    System.out.println(Arrays.toString(floatArrayCopy) + " - Copy");
    floatArrayCopy[1] = floatArrayCopy[0];
    System.out.println(Arrays.toString(floatArray) + " - Original");
    System.out.println(Arrays.toString(floatArrayCopy) + " - Copy");
  }
}
