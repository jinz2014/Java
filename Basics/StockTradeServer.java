import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class StockTradeServer {

  public static void main(String[] args) {
    try {
      Thread tradeGenerator = new Thread (
          new StockTradeGenerator()); 
      tradeGenerator.setDaemon(true);
      tradeGenerator.start();
      while (System.in.read() != '\n') {}
    } 
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  private static class StockTradeGenerator implements Runnable {

    private DatagramSocket broadcastSocket = null;
    private String[] stockName = {"IBM", "SNE", "XRX", "MHP"};
    private static final String TIME = "hh:mm:ss";

    // ctor
    public StockTradeGenerator () {
      try {
        broadcastSocket = new DatagramSocket(4445);
      } catch (SocketException e) {
        System.out.println(e.getMessage());
      }
    }

    public void run() {
      byte[] buffer = new byte[80];
      try {
        while (true) {
          // select randomly a stock trade to send
          int index = (int) (Math.random() * 4);
          float trade = generateRandomTrade(index);
          String lastTrade = String.format("%s %.2f @%s", 
              stockName[index], trade, now());
          buffer = lastTrade.getBytes(); 

          try {

            // the packet data in bytes and its length (length <= buf.length)
            // The destination address and the destination port number
            InetAddress broadcastAddr = InetAddress.getByName("230.0.0.1");

            /* Constructs a datagram packet for *sending* packets of length 
               length to the specified port number on the specified host.
             */
            DatagramPacket packet = new DatagramPacket (
                buffer, buffer.length, broadcastAddr, 4446); 

            broadcastSocket.send(packet); 
            Thread.sleep((long)(Math.random()*2000));
          } catch (Exception e) {
            System.out.println(e.getMessage());
          }
        }
      } finally {
        broadcastSocket.close();
      }
    }

    private float generateRandomTrade(int index) {
      float trade = (float) Math.random();

      switch(index) {
        case 0: 
          trade += 118;
          break;
        case 1: 
          trade += 18;
          break;
        case 2: 
          trade += 29;
          break;
        case 3: 
          trade += 14;
          break;
      }
      return trade;
    }

    private String now() {
      Calendar c = Calendar.getInstance();
      SimpleDateFormat f = new SimpleDateFormat(TIME);
      return f.format(c.getTime());
    }
  }
}

