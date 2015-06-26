/**
  * Copy file using "byte stream" read/write
  *
  * read
  * The function read(byte[] b)  reads the number of bytes equal to the
  * length of the byte array. 
  * Return the actual number of read bytes
  *
  * void write (byte[] b, int off, int len) throws IOException
  * Writes len bytes from the specified byte array starting at offset off 
  * to this output stream. The general contract for write(b, off, len)
  * is that some of the bytes in the array b are written to the output stream in order;
  * element b[off] is the first byte written and 
  * b[off+len-1] is the last byte written by this operation. 
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
