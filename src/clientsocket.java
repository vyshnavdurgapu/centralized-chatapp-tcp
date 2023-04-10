import java.io.*;
import java.net.*;

class clientsocket {
	Socket s ;
	BufferedReader in;
	PrintWriter out;
	
	clientsocket() throws UnknownHostException, IOException
	{
		s=new Socket("localhost",9999);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out  = new PrintWriter(s.getOutputStream());
	}
	void sendtext(String s)
	{
		out.println(s);
		out.flush();
	}
	String  revievetext()
	{
		try {
			return in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
