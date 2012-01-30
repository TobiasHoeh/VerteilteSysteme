import java.io.IOException;
import java.net.Socket;

public class Worker extends Thread {
	private Socket socket;
	private GameMonitor monitor;

	public Worker(Socket socket, GameMonitor monitor) {
		this.socket = socket;
		this.monitor = monitor;
	}

	public void run() {
		monitor.startGame();
		System.out.println("Playing Game");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.endGame();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
