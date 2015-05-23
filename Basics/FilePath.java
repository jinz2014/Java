import java.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class FilePath {
  public static void main (String[] args) {
    // convert to window style
    Path p = Paths.get("/home/Jim/Java/dictionary.txt");
    // print the path
    System.out.println(p);
    // home
    System.out.println( p.getName(0) );
    // 4
    System.out.println( p.getNameCount() );
    // dictionary.txt
    System.out.println( p.getName(p.getNameCount()-1) );
    // dictionary.txt
    System.out.println( p.getFileName() );
    // \home\Jim
    System.out.println( p.subpath(0,2) );
    // \home\Jim\Java
    System.out.println( p.getParent() );
    // \
    System.out.println( p.getRoot() );

    try (BufferedReader in = new BufferedReader (new FileReader(args[0]))) {
    /*
    Charset charset = Charset.forName("US-ASCII");
    Path p = Paths.get(args[0]);
    try (BufferedReader in = Files.newBufferedReader(p, charset)) {
    */

      String line;
        while ((line = in.readLine()) != null) {
          System.out.println(line);
        }
    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    };
  }
}
