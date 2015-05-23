/**
  * Copy file using "byte stream" read/write
  *
  *  The function read(byte[] b)  reads the number of bytes equal to the
  *  length of the byte array. 
  *  Return the actual number of read bytes
  *
  *  Java SE7 and above
  */

import java.io.*;

public class FileCopy {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage java FileCopy <src filename> < dst filename");
      System.exit(0);
    }
    int numRead = 0;
    byte[] buffer = new byte[512];

    // InputStream is a superclass of FileInputStream
    try (InputStream reader = new FileInputStream(args[0]);
         OutputStream writer = new FileOutputStream(args[1])) {
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
