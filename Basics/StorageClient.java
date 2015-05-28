
import java.io.*;
import java.net.*;
import java.util.logging.*;

public class StorageClient {

  public static void main(String[] args) {
    final String putCmd = "put";
    final String getCmd = "get";
    if (args.length < 2) {
      System.out.println("Usage: java StorageClient get/put <filename>");
      System.exit(0);
    }

    int cmd = 0;
    switch(args[0]) {
      case putCmd:
        cmd = 1;
        break;
      case getCmd:
        cmd = 0;
        break;
    }

    String fileName = args[1];

    try (Socket requestSocket = new Socket()) {

      // create a client socket and establish the connection to the server
      requestSocket.connect(new InetSocketAddress("localhost", 10000));

      // create writer(client->server) and reader(client<-server) 
      DataOutputStream writer = new DataOutputStream (requestSocket.getOutputStream());
      DataInputStream reader  = new DataInputStream (requestSocket.getInputStream());

      // send protocol to server
      writer.writeInt(cmd);
      writer.writeUTF(fileName);

      // send file content to server (upload)
      if (cmd == 1) {
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String str = null; 
        while ((str = fileReader.readLine()) != null) {
          writer.writeUTF(str);
        }
        writer.writeUTF("-1");
        writer.close();
        fileReader.close();
        System.out.println(fileName + " uploaded successfully");
      } else {
      // receive file content from server (download)
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));
        String str = null;
        while (!(str = reader.readUTF()).equalsIgnoreCase("-1")) {
          fileWriter.write(str);
          fileWriter.newLine();
        }
        reader.close();
        fileWriter.close();
        System.out.println(fileName + " downloaded successfully");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

