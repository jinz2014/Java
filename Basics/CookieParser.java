import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class CookieParser {
  private final static String TIME = "hh:mm:ss";
  private final static SimpleDateFormat sdf = 
    new SimpleDateFormat(TIME);

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Example usage: java CookieParser http://google.com");
      System.exit(0);
    }

    try {
      // http://www.yahoo.com
      String urlString = args[0];

      // an implementation of CookieHandler
      CookieManager cm = new CookieManager();
      cm.setCookiePolicy(new CustomCookiePolicy());
      CookieHandler.setDefault(cm);

      URL url = new URL(urlString);
      URLConnection con = url.openConnection();

      // general syntax of HTTP response:
      // HTTP/1.1 200 OK
      // Content-type: text/html
      // Set-Cookie: usr=sanjay
      // Set-Cookie: password =10101; Expires = ...
      //...
      Object obj = con.getContent();

      List<HttpCookie> cookies = cm.getCookieStore().getCookies();

      System.out.println("Number of cookies : " + cookies.size());

      for (HttpCookie c : cookies) {
        System.out.println("Name: " + c.getName());
        System.out.println("Domain: " + c.getDomain());
        long age = c.getMaxAge();
        if (age == -1)
          System.out.println("The cookier will expire when browser closes");
        else
          System.out.printf("The cookier will expire in %s" 
              + "seconds%n", sdf.format(age));

        System.out.println("Secure: " + 
          ((Boolean) c.getSecure()).toString());

        System.out.println("Value: " + c.getValue());
        System.out.println();
      }
    }
    catch(MalformedURLException e) {
      System.out.println("Invalid URL");
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}

class CustomCookiePolicy implements CookiePolicy {
  public boolean shouldAccept(URI uri, HttpCookie cookie) {
    // not accept any cookie from google.com
    //return uri.getHost().equals("google.com");

    // accept any cookie
    return true;
  }
}

