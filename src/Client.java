// Client.java
// The Client recieves an alphanumeric sequence
// from the user and sends it to the server.
// The Client outputs the reversed sequence to
// the user once it is recieved.

import java.io.*;
import java.net.*;

public class Client
{
  public static void main(String[] args)
    throws IOException
  {
    // Host name
    final InetAddress HOST_ADDR =
      InetAddress.getByName("localhost");

    // Server's PORT ID
    final int SERVER_PORT = 5000;

    Socket          s  = null;
    BufferedReader in  = null, stdin = null;
    PrintWriter    out = null;
    try
    {
      // Create an input socket
      s   = new Socket(HOST_ADDR, SERVER_PORT);
      out = new PrintWriter(s.getOutputStream(), true);
      in  = new BufferedReader(
        new InputStreamReader(s.getInputStream())
      );

      stdin = new BufferedReader(
        new InputStreamReader(System.in)
      );

      // Get input from user
      System.out.print("Insert an alphanumeric sequence: ");
      String ti = stdin.readLine();
      if(ti != null)
      {
        // Send the sequence to the Server
        out.println(ti);
        
        // Show the response to the user
        System.out.println("Inverted sequence: " + in.readLine());
      }
    }
    finally
    {	if(stdin != null) stdin.close();
      if(in    != null) in.close();
      if(out   != null) out.close();
      if(s     != null) s.close();
    }
  }
}

