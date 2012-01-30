import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(4444);
			Socket connection;
			GameMonitor monitor = new GameMonitor();
			while (true) {
				connection = server.accept();
				Worker worker = new Worker(connection, monitor);
				worker.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
