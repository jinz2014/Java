/**
  * Calculate file length in the number of bytes
  *
  * Java SE7 and above
  */

import java.io.*;

public class FileLength {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: java FileLength <filename>");
      System.exit(0);
    }
    int count = 0;
    // InputStream is a superclass of FileInputStream
    try (InputStream reader = new FileInputStream(args[0])) {
      while (reader.read() != -1) {
        count++;
      }
      System.out.print(args[0] + " length in byte = " + count);
    } 
    catch (FileNotFoundException e) {
      System.out.print("File " + args[0] + " not found.");
      System.exit(0);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
