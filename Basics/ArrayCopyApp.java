import java.util.Arrays;

/*
   Array copy methods:
     dstArray = srcArray.clone();
     dstArray = Arrays.copyOf(srcArray)
     System.arraycopy(srcArray, 0, dstArray, 0, length)
 */
public class ArrayCopyApp {

  // Print an array without using iteration
  public static void arrayPrint(float[] arr, String name) {
    System.out.println(Arrays.toString(arr) + " - " + name);
  }

  public static void main (String[] args) {

    float[] floatArray = {1.0f, 3.5f, 4.1f};

    //----------------------------------------------
    //----------------------------------------------
    // copy
    float[] floatArrayCopy = floatArray.clone();

    // modify
    floatArrayCopy[1] = floatArrayCopy[0];
    arrayPrint(floatArray, "Original");
    arrayPrint(floatArrayCopy, "Copy");

    //----------------------------------------------
    // note the src and destination array parameters
    //----------------------------------------------
    // copy
    float[] floatArrayCopy2 = new float[3];
    System.arraycopy(floatArray, 0,  floatArrayCopy2, 0,  floatArray.length);

    // modify
    floatArrayCopy2[1] = floatArrayCopy2[0];
    arrayPrint(floatArrayCopy2, "Copy2");

    //----------------------------------------------
    //----------------------------------------------
    float[] floatArrayCopy3 = Arrays.copyOf(floatArray, floatArray.length);
    floatArrayCopy3[1] = floatArrayCopy3[0];
    arrayPrint(floatArrayCopy3, "Copy3");
  }
}
