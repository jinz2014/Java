import java.util.Arrays;

/* Insertion sort an array */

public class InsertionSortTest {
  public static void main(String[] args) {
    int sz =  Integer.parseInt(args[0]);
    Double[] arr = new Double[sz];
    Double[] arr1 = new Double[sz];
    for (int i = 0; i < sz; i++) {
      arr[i] = sz * Math.random();
      arr1[i] = arr[i];
    }
    insertionSort(arr, 0, sz-1);
    Arrays.sort(arr1);
    if (false == Arrays.equals(arr, arr1)) {
      System.out.println("[Error] insertion sort doesn't match Arrays.sort");
    }
  }

  private static void insertionSort(Double[] objectArray,
                                    int lowerIndex, 
                                    int upperIndex) {
    try {
      for (int i = lowerIndex + 1; i <= upperIndex; i++) {
        Double key = objectArray[i];
        int j = i-1;
        while (j >= lowerIndex && key.compareTo(objectArray[j]) < 0) {
          objectArray[j+1] = objectArray[j];
          j--;
        }
        objectArray[j+1] = key; 
      }
    } 
    catch (NullPointerException e) {
      System.out.printf("NullPointerException: lowerIndex = %d upperIndex = %d%n", lowerIndex, upperIndex);
    }
  }
}

