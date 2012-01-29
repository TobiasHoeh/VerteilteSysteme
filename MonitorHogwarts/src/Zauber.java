public class Zauber implements Runnable {

	private int haus;
	private HogwartsMonitor monitor;
	private String zauberSpruch;

	public Zauber(int haus, HogwartsMonitor monitor, String zauberSpruch) {
		this.haus = haus;
		this.monitor = monitor;
		this.zauberSpruch = zauberSpruch;
	}

	@Override
	public void run() {
		monitor.jinxSpoken(haus);
		// Zaubern
		System.out.println("Haus: " + haus + " Spruch: " + zauberSpruch);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		monitor.jinxDone();
	}
}
