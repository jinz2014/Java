/**
  * Copy file using "character stream" read/write
  *
  * PrintWriter provides method that can write int, long and other
  * primitive data types as text rather than as their byte values
  * Java SE7 and above
  */

import java.io.*;

public class FileViewer {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage java FileViewer <src filename>");
      System.exit(0);
    }
    int numRead = 0;
    char[] buffer = new char[512];
    // InputStream is a superclass of FileInputStream
    try (FileReader reader = new FileReader(args[0]);
         PrintWriter writer = new PrintWriter(System.out)) {
         //FileWriter writer = new FileWriter(System.out)) {
      while ( (numRead = reader.read(buffer)) != -1) {
        writer.write(buffer, 0, numRead);
      }
    } 
    catch (FileNotFoundException e) {
      // create a file to write if its filename doesn't exist
      System.out.print("File " + args[0] + " not found.");
      System.exit(0);
    }
    catch (IOException e) {
      System.out.print("Error reading/writing file");
    }
  }
}

