import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 4444);
			Socket socket2 = new Socket("localhost", 4444);
			Socket socket3 = new Socket("localhost", 4444);
			Socket socket4 = new Socket("localhost", 4444);
			Socket socket5 = new Socket("localhost", 4444);
			Socket socket6 = new Socket("localhost", 4444);
			Socket socket7 = new Socket("localhost", 4444);
			Socket socket8 = new Socket("localhost", 4444);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
