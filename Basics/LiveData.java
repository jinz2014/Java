import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/*
  ByteArrayOutputStream writes data to its internal buffer. It only deals with
  the raw binary data. Use DataOutputStream to get the abstraction to higher
  data types
  ByteArrayInputStream buffers the data received from a socket connection and 
  then use DataInputStream class to convert the raw data to primitive data types
  */




public class LiveData {
  private ByteArrayOutputStream outStream;

  public static void main(String args[]) { //throws IOException {
    LiveData app = new LiveData();
    app.createData();
    app.readData();
  }

  public void createData() {
    try {
      outStream = new ByteArrayOutputStream();
      DataOutputStream writer = new DataOutputStream(outStream); 
      for (int i = 0; i < 5; i++) {
        Trade t = new Trade(i);
        writer.writeInt(t.scriptCode);
        writer.write(t.time);
        writer.writeDouble(t.bid);
        writer.writeDouble(t.offer);
        writer.writeDouble(t.high);
        writer.writeDouble(t.low);
        writer.writeLong(t.quantity);
      }
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void readData() {
    byte[] time_buffer = new byte[8];
    //StringBuilder sb = new StringBuilder();
    //Formatter fmt = new Formatter(sb, Locale.US);

    ByteArrayInputStream inStream = 
      new ByteArrayInputStream (outStream.toByteArray());
    DataInputStream reader = new DataInputStream(inStream); 
    try {
      for (int i = 0; i < 5; i++) {
        int scriptCode = reader.readInt();
        reader.read(time_buffer);
        String time   = new String(time_buffer);
        double bid    = reader.readDouble();
        double offer  = reader.readDouble();
        double low    = reader.readDouble();
        double high   = reader.readDouble();
        long quantity = reader.readLong();
        //formatter.format("...");
        System.out.printf("Scriptcode: %2d" + 
            "\tTime: %s" + 
            "\tbid: %5.2f" +
            "\toffer: %5.2f" + 
            "\tlow: %5.2f" + 
            "\thigh: %5.2f" + 
            "\tquantity: %d" +
            "\n", 
            scriptCode, time, bid, offer, low, high, quantity);
      }
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private class Trade {
    private int    scriptCode;
    private byte[] time;
    private double bid, offer, high, low;
    private long   quantity; 

    // ctor
    public Trade(int i) {
      scriptCode = i + 1;
      time = now("hh:mm:ss").getBytes();
      bid = Math.random()*100;
      offer = Math.random()*100;
      high = Math.random()*100;
      low = Math.random()*100;
      quantity = (long)(Math.random()*10000);
    }
  }

  private String now(String dateFormat) {
    SimpleDateFormat f = new SimpleDateFormat(dateFormat);
    Calendar cal = Calendar.getInstance();
    return f.format(cal.getTime());
  }
}


