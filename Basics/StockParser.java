public class StockParser {

  // stock name, trade date, opening, low, high, closing price, volume
  private static String[] attr = {
    "stock name",
    "stock date",
    "opening price",
    "low price",
    "high price",
    "closing price",
    "volume"
  };

  private static String trade = "IBM,09/10/2009,87,100,80,95,156783";
  private static String trade2 = "IBM,09/09/2009,87,100,80,95,156783";

  public static void main(String[] args) {

    //-----------------------------------------------------
    // byte-charset
    //-----------------------------------------------------
    // encode the string into a sequence of bytes 
    // using the platform's default charset;
    System.out.println("What is Charset?");
    String n = "123";
    byte[] encode = n.getBytes();
    for (byte b : encode) {
      System.out.print(b + " "); // 49 50 51
    }
    System.out.println();

    // decode the specified subarray 
    // using the platform's default charset;
    String nd = new String(encode);
    System.out.println("Equal ? " + n.equals(nd));

    //-----------------------------------------------------
    // String comparisions
    //-----------------------------------------------------
    // true/false
    System.out.println("Equal trades ? " + trade.equals(trade2));
    // 0
    System.out.println("Compare same trades ? " + trade.compareTo(trade));
    // 1
    System.out.println("Compare two trades ? " + trade.compareTo(trade2));
    // -1
    System.out.println("Compare two trades ? " + trade2.compareTo(trade));

    System.out.println("Hash code of trade: " + trade.hashCode());

    //-----------------------------------------------------
    // substring
    //-----------------------------------------------------
    String date = trade.substring(4,14);  // [4,14)
    System.out.println("trade date is " + date);

    //-----------------------------------------------------
    // contains
    //-----------------------------------------------------
    if (trade.contains("09/10/2009")) {
      System.out.println("trade date is 09/10/2009");
    }

    //-----------------------------------------------------
    // replace
    //-----------------------------------------------------
    String str = trade.replace(',', ':');
    System.out.println("After replacing delimiter ',': " + str);

    str = trade.replace("100", "101");
    System.out.println("After replacing trade price 100: " + str);

    String a = "101";
    String b = "100";
    str = trade.replace(a, b);
    System.out.println("After replacing trade price 100: " + str);

    //-----------------------------------------------------
    // list the stock attributes and values
    //-----------------------------------------------------
    String[] fields = trade.split(",");
    int i = 0;
    for (String s : fields) {
      System.out.println(String.format("%s\t%s", attr[i++], s));
    }


    //-----------------------------------------------------
    // static methods String.valueOf(...)
    //-----------------------------------------------------
    float diff = Float.parseFloat(fields[3]) - Float.parseFloat(fields[4]);
    str = String.valueOf(diff);
    System.out.println("Price difference from hi to low" + str);
    System.out.println(String.format("Formatted difference $%.02f", diff));
  }
}
