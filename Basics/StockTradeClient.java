import java.io.*;
import java.net.*;

/*
   MulticastSocket

 */

public class StockTradeClient {

  public static void main(String[] args) {
    try {
      MulticastSocket socket = new MulticastSocket(4446);
      InetAddress address = InetAddress.getByName("230.0.0.1");
      socket.joinGroup(address);

      for (int i = 0; i < 10; i++) {
        byte[] buffer = new byte[256];
        /* Constructs a DatagramPacket for *receiving* packets of length length.

           The length argument must be less than or equal to buf.length.

           Parameters:
              buf - buffer for holding the incoming datagram.
              length - the number of bytes to read.
         */
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        /*
           decode the packet byte data into characters using the platform's charset
         */
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Trade: " + received);
      }
      socket.leaveGroup(address);
      socket.close();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}


