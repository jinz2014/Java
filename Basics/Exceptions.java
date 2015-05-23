import java.io.*;
import java.net.*;

public class Exceptions {
  public static void main(String[] args) {
    String url = null;
    while (true) {
      try {
        System.out.print("Enter URL: ");
        BufferedReader reader = new BufferedReader (
            new InputStreamReader(System.in));
        url = reader.readLine();

        if (url.length() == 0) {
          System.out.println("No URL specified:");
          continue;
        }
        System.out.println("Opening " + url);

        URL url1 = new URL(url);
        reader = new BufferedReader (
            new InputStreamReader(url1.openStream()));
        System.out.println(reader.readLine());
        reader.close();
      } 
      catch (MalformedURLException e) {
        System.out.println("Invalid URL " + url + e.getMessage());
      } 
      catch (IOException e) {
        System.out.println("Unable to execute " + url + e.getMessage());
      } 
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
