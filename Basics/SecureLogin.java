import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class SecureLogin {
  private final int MAX_LOGIN = 3;

  private boolean login() {
    Console c = System.console();
    boolean isAuthenticated = false;
    if (c != null) {
      int count = 0;
      do {
        char[] pwd = c.readPassword("%s", "Password: ");
        isAuthenticated = authenticate(pwd);
        // a primitive array can be deterministically cleared from
        // memory as opposed to a String or other container
        Arrays.fill(pwd, ' ');
        count++;
      } while (!isAuthenticated && count <= MAX_LOGIN);
    }
    return isAuthenticated; 
  }

  private boolean authenticate(char[] pwd) {
    char[] secret = {'p', 'a', 's', 's'};
    if (Arrays.equals(pwd, secret)) {
      Arrays.fill(pwd, ' ');
      return true;
    } else {
      System.out.print("Authentication failed");
      return false;
    }
  }

  private String now(String dateFormat) {
    SimpleDateFormat f = new SimpleDateFormat(dateFormat);
    Calendar cal = Calendar.getInstance();
    return f.format(cal.getTime());
  }

  public static void main(String[] args) {
    SecureLogin app = new SecureLogin();
    if (app.login()) {
      System.out.print("Login at " + app.now("hh:mm:ss"));
    } else {
      System.out.print("Login failed");
      System.exit(0);
    }
  }

}


