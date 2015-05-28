import java.util.Arrays;
import java.util.concurrent.*;

/*
   For 1,000,000 elements,

   insertionSort takes time ~330us
   Arrays.sort takes time ~530 us
 */

public class ParallelMergeSort {

  private static ForkJoinPool threadPool;
  private final static int TD = 16;

  private static void psort(Double[] array) {
    Double[] dstArray = new Double[array.length];
    threadPool.invoke(new SortTask(array, dstArray, 0, array.length - 1));
  }

  static class SortTask extends RecursiveAction {
    private Double[] srcArray;
    private Double[] dstArray;
    private int lowerIndex;
    private int upperIndex;

    public SortTask ( Double[] srcArray, Double[] dstArray, 
                      int lowerIndex, int upperIndex) {
      this.srcArray = srcArray;
      this.dstArray = dstArray;
      this.lowerIndex = lowerIndex;
      this.upperIndex = upperIndex;
    }

    @Override
    protected void compute() {
      if (upperIndex - lowerIndex < TD) {
        //----------------------------------------------------
        //  Use either insertionSort or Arrays.sort 
        //----------------------------------------------------
        //insertionSort(srcArray, lowerIndex, upperIndex);
        Arrays.sort(srcArray, lowerIndex, upperIndex+1);
        return;
      }

      int midIndex = (lowerIndex + upperIndex) >>> 1;

      invokeAll(new SortTask(srcArray, dstArray, lowerIndex, midIndex),
                new SortTask(srcArray, dstArray, midIndex+1, upperIndex));

      merge(srcArray, dstArray, lowerIndex, midIndex, upperIndex);
    }
  }

  private static void merge (Double[] srcArray, 
                             Double[] dstArray, 
                             int lowerIndex, 
                             int midIndex, 
                             int upperIndex) {
    if (srcArray[midIndex].compareTo(srcArray[midIndex+1]) <= 0) {
      return;
    }

    System.arraycopy(srcArray, lowerIndex,
        dstArray, lowerIndex, midIndex - lowerIndex + 1);

    int k = lowerIndex;
    int i = lowerIndex;
    int j = midIndex + 1;

    while (k < j && j <= upperIndex) {
      if (dstArray[i].compareTo(srcArray[j]) <= 0) {
        srcArray[k++] = dstArray[i++];
      } else {
        srcArray[k++] = srcArray[j++];
      }
    }

    if (j > upperIndex) // remaining data in dstArray
      System.arraycopy(dstArray, i, srcArray, k, j - k);
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

  private static Double[] createRandomData(int length) {
    Double[] data = new Double[length];
    for (int i = 0; i < data.length; i++) {
      data[i] = (length * Math.random());
    }
    return data;
  }

  public static void main (String[] args) {
    if (args.length < 1) {
      System.out.printf("Usage: java -cp . ParallelMergeSort <Size of array>");
      System.exit(0);
    }
    int SZ = Integer.parseInt(args[0]);
    int proc = Runtime.getRuntime().availableProcessors();
    System.out.printf("The platform has %d processors.%n", proc);

    threadPool = new ForkJoinPool(proc);
    Double[] data  = createRandomData(SZ);
    Double[] data1 = data.clone();
    //System.out.println(Arrays.toString(data));

    long start = System.nanoTime();
    psort(data);
    long elap = System.nanoTime() - start;

    System.out.println("Elapsed time is " + elap);

    //System.out.println(Arrays.toString(data));

    Arrays.sort(data1);
    //System.out.println(Arrays.toString(data1));

    if (false == Arrays.equals(data, data1)) {
      System.out.println("[Error] Parallel sort doesn't match Arrays.sort");
      for (int i = 0; i < SZ; i++) {
        if (data[i] != data1[i]) {
          System.out.println("Data mismatch at index " + i);
        }
      }
    } else {
      System.out.println("[Info] Parallel sort finished successfully");
    }
  }
}

