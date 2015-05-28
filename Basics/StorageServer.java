import java.io.*;
import java.net.*;
import java.util.logging.*;

public class StorageServer {

  private static ServerSocket server;

  public static void main(String[] args) {
    Socket requestSocket = null;
    new Thread(new Monitor()).start();

    try {
      // create a server socket
      server = new ServerSocket(10000);
      System.out.println("Server started:");
      try {
        while (true) {
          // await client requests. 
          requestSocket = server.accept();

          // create a thread to process the request
          new Thread(new RequestProcessor(requestSocket)).start();
        }
      }
      finally {
        requestSocket.close();
      }
    } 
    catch (Exception e) {
      Logger.getLogger(StorageServer.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  private static class RequestProcessor implements Runnable {
    private Socket requestSocket;
    public RequestProcessor (Socket requestSocket) {
      this.requestSocket = requestSocket;
    }

    public void run() {
      try (DataInputStream reader = new DataInputStream(requestSocket.getInputStream());
           DataOutputStream writer = new DataOutputStream(requestSocket.getOutputStream())) {

        // pototocl read
        int cmd = reader.readInt();
        String fileName = reader.readUTF();

        String message;
        if (cmd == 1) 
          message = "Put ";
        else
          message = "Get ";
        message += fileName + " requested";
        System.out.println(message);

        if (cmd == 1) 
          uploadFile(reader, fileName);
        else
          downloadFile(writer, fileName);
      }
      catch (IOException e) {
        Logger.getLogger(StorageServer.class.getName()).log(Level.SEVERE, null, e);
      }
    }
  }

  private static void uploadFile(DataInputStream in, String fileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("server-" + fileName))) {
      // read from the input stream and writes to the output file created
      // in the server's current working directory
      String str;
      while (!(str = in.readUTF()).equals("-1")) {
        System.out.println(str);
        writer.write(str);
        writer.newLine();
      }
      in.close();
    } catch (IOException e) {
      Logger.getLogger(StorageServer.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  private static void downloadFile(DataOutputStream out, String fileName) {
      // read from the output file created in the server's current working directory
      // and send the file content to the output stream
    try (BufferedReader reader = new BufferedReader(new FileReader("server-" + fileName))) {
      String str = reader.readLine();
      while (str != null) {
        out.writeUTF(str);
        str = reader.readLine();
      }
      out.writeUTF("-1");
      out.close();
    } catch (IOException e) {
      Logger.getLogger(StorageServer.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  private static void shutdownServer() {
    try {
      server.close();
    } catch (IOException e) {
      Logger.getLogger(StorageServer.class.getName()).log(Level.SEVERE, null, e);
    }
    System.exit(0);
  }

  // Monitor the event when Enter key is hit
  private static class Monitor implements Runnable {
    public void run() {
      try {
        while (System.in.read() != '\n') {}
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      shutdownServer();
    }
  }
}

