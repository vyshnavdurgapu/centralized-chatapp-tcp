import java.io.*;
import java.net.*;

class user implements Runnable
{
	Socket s1;
	Socket s2;
	
	BufferedReader in1;
	PrintWriter out2;
	
	user(Socket s1,Socket s2) throws IOException
	{
		// server socket who interrupted 
		// except for that socket every other user broadcast
		this.s1 = s1;
		this.s2 = s2;
		
		in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
		out2 = new PrintWriter(s2.getOutputStream(),true);
	}

	@Override
	public void run() {
		while(true)
		{
			try {
				String str =in1.readLine();
				out2.println(str);
				out2.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class serversocket{

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("waiting for connection");
			Socket s1 = ss.accept();
			System.out.println("1st user connected");
			Socket s2 = ss.accept();
			System.out.println("2st user connected");
			
			user u1 = new user(s1,s2);
			user u2 = new user(s2,s1);
			// msg from s1 
			Thread t1 = new Thread(u1);
			Thread t2 = new Thread(u2);
			
			t1.start();
			t2.start();
		}
		catch(Exception e)
		{
			
		}
	}
}
