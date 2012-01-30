public class GameMonitor {
	public int activePlayers;

	public GameMonitor() {
		activePlayers = 0;
	}

	public synchronized void startGame() {
		if (activePlayers >= 2) {
			try {
				System.out.println("WAITING FOR GAME");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		activePlayers++;
	}

	public synchronized void endGame() {
		activePlayers--;
		notify();
	}

}
