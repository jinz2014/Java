import java.net.*;

public class DNS_Lookup {
  public static void main(String[] args) {
    try {
      InetAddress[] inetHost = null;

      System.out.println("List of Google servers");
      inetHost = InetAddress.getAllByName("www.google.com");
      for (InetAddress addr : inetHost) {
        System.out.println(addr);
      }

      System.out.println();
      System.out.println("List of CNN servers");
      inetHost = InetAddress.getAllByName("www.cnn.com");
      for (InetAddress addr : inetHost) {
        System.out.println(addr);
      }

      System.out.println();
      System.out.println("List of Univ. of South Carolina servers");
      inetHost = InetAddress.getAllByName("www.sc.com");
      for (InetAddress addr : inetHost) {
        System.out.println(addr);
      }

      System.out.println();
      System.out.println("List of Local servers");
      //System.out.println(InetAddress.getLocalHost().toString());
      inetHost = InetAddress.getAllByName("localhost");
      for (InetAddress addr : inetHost) {
        System.out.println(addr);
      }
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }
}
