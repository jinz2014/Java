// ---------------------------------------------------------------------
// 1 Scanner for file read
// 2 FileWriter for file write
// 3 throws exception 
// 4 remember to close the file
// ---------------------------------------------------------------------
import java.io.*;
import java.util.*;

class for_each {

  static List<Integer> readIntegerFromFile(String fn) throws IOException {
    Scanner s = new Scanner(new File(fn));
    List<Integer> txt = new ArrayList<Integer>();
    while (s.hasNextInt()) {  // No for (Integer v : s)
      txt.add(s.nextInt());
    }
    s.close();
    return txt;
  }

  static void writeIntegerToFile(String fn, List<Integer> lst) throws IOException {

    Writer w = new FileWriter(fn);
    for (Integer v : lst) {
      System.out.println(v);
      w.write(v+" ");
    }
    w.close(); // must close the file
  }

  public static void main(String[] args) {
    try { 
      List<Integer> txt = readIntegerFromFile("unsorted_data.txt");
      Collections.sort(txt);
      writeIntegerToFile("sorted_data.txt", txt);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

}
