import java.io.*;
//import java.io.LineNumberReader;

/**
  try a few class methods
 */
public class LineCounter {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage java LineCounter <src filename>");
      System.exit(0);
    }

    try (LineNumberReader reader = new LineNumberReader(new FileReader(args[0]))) {
      reader.mark(4096); // mark is invalid if reading more than 4096 chars
      while ( reader.readLine() != null) {
        // read all lines
      }
      System.out.printf("File %s has %d lines%n", args[0], reader.getLineNumber());

      reader.reset(); // must call mark() before reset();
      System.out.printf("After reset File %s has %d lines%n", args[0], reader.getLineNumber());
    } 
    catch (FileNotFoundException e) {
      // create a file to write if its filename doesn't exist
      System.out.print("File " + args[0] + " not found.");
      System.exit(0);
    }
    catch (IOException e) {
      e.printStackTrace();
      //System.out.print("Error reading/writing file");
    }
  }
}


