// Server.java
// A Server that flips alphanumeric sequences

import java.io.*;
import java.net.*;

public class Server
{
  public static void main(String[] args)
    throws IOException
  {
    // PORT ID
    final int PORT = 5000;

    // Listen to PORT
    ServerSocket ss = new ServerSocket(PORT);

    try
    {
      while(true)
      {
        Socket         s   = null;
        PrintWriter    out = null;
        BufferedReader in  = null;
        try
        {
          
          // W8 for request from client
          s = ss.accept();
          
          in  = new BufferedReader(
            new InputStreamReader(s.getInputStream())
          );

          // Read the alphanumeric sequence
          String alpha_num_seq = in.readLine();
          
          // Flip it and return the result to the client
          String flipd_seq = flipSeq(alpha_num_seq);
                 out       = new PrintWriter(s.getOutputStream(), true);
        
          out.println(flipd_seq);
        }
        finally
        {
          if(in  != null) in.close();
          if(out != null) out.close();
          if(s   != null) s.close();
        }
      }
    }
    finally
    {
      if(ss != null) ss.close();
    }
  }

  // Inverts an alphanumeric sequence
  private static String flipSeq(String s)
  {
    StringBuilder sb = new StringBuilder(s);
    return sb.reverse().toString();
  }
}

