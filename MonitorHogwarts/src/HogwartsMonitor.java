public class HogwartsMonitor {
	private final int Gryffindor = 0;
	private final int Slytherin = 1;
	private final int Ravenclaw = 2;
	private final int Huffelpuff = 3;

	private boolean activeJinx = false;
	private int gQueue = 0;
	private int sQueue = 0;
	private int rQueue = 0;
	private int hQueue = 0;

	public HogwartsMonitor() {

	}

	public synchronized void jinxSpoken(int haus) {
		if (activeJinx == true || gQueue != 0 || sQueue != 0 || rQueue != 0
				|| hQueue != 0) {
			try {
				switch (haus) {
				case 0:
					gQueue++;
					while (activeJinx == true || sQueue != 0 || rQueue != 0
							|| hQueue != 0) {
						wait();
					}
					gQueue--;
					break;
				case 1:
					sQueue++;
					while (activeJinx == true || rQueue != 0 || hQueue != 0) {
						wait();
					}
					sQueue--;
					break;
				case 2:
					rQueue++;
					while (activeJinx == true || hQueue != 0) {
						wait();
					}
					rQueue--;
					break;
				case 3:
					hQueue++;
					while (activeJinx == true) {
						wait();
					}
					hQueue--;
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		activeJinx = true;
	}

	public synchronized void jinxDone() {
		activeJinx = false;
		notifyAll();
	}
}
