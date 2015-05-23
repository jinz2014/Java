/**
  * centralized exception handler using throw 
  *
  * The data.txt file is formatted as (e.g.):
  * 12
  * 13
  * 14
  * Each line contains only one number
  */

import java.io.*;
import java.net.*;

public class ExceptionHandler {
  private static BufferedReader reader = null;

  private void openDataFile(String filename) 
    throws FileNotFoundException, IOException {
    reader = new BufferedReader(new FileReader (filename));
  }

  private void readData() 
    throws IOException, NumberFormatException {
    String str;
    while ((str = reader.readLine()) != null) {
      int n = Integer.parseInt(str);
      System.out.println(n);
    }
  }

  public static void main(String[] args) {
    String url = null;
    try {
      ExceptionHandler app = new ExceptionHandler();
      app.openDataFile("data.txt");
      app.readData();
      reader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("data.txt not found");
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
    catch (NumberFormatException e) {
      System.out.println("data.txt has invalid number format");
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

}
