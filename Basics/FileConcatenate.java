/**
  * accepts a variable number of arguments
  *
  */
import java.io.*;

public class FileConcatenate {

  public static void cat(String ... filenames) 
    throws IOException, FileNotFoundException  {
    String str = null;
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("concat.txt"))) {
      for (String filename : filenames) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          while ((str = reader.readLine()) != null) {
            writer.write(str);
            writer.newLine();
          }
        } 
      }
    }
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.print("Usage java FileConcatenate file1 [file2]");
      System.exit(0);
    }
    try {
      cat(args);
    }
    catch (FileNotFoundException e) {
      System.out.print(e.getMessage());
    }
    catch (IOException e) {
      System.out.print(e.getMessage());
    }
  }
}
