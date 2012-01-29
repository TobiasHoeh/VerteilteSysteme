public class HogwartsMonitor {
	private final int Gryffindor = 0;
	private final int Slytherin = 1;
	private final int Ravenclaw = 2;
	private final int Huffelpuff = 3;

	private boolean activeJinx = false;
	private int[] queue = new int[4];

	public HogwartsMonitor() {

	}

	public synchronized void jinxSpoken(int haus) {
		try {
			switch (haus) {
			case 0:
				queue[Gryffindor]++;
				while (activeJinx == true || queue[Slytherin] != 0
						|| queue[Ravenclaw] != 0 || queue[Huffelpuff] != 0) {
					wait();
				}
				queue[Gryffindor]--;
				break;
			case 1:
				queue[Slytherin]++;
				while (activeJinx == true || queue[Ravenclaw] != 0
						|| queue[Huffelpuff] != 0) {
					wait();
				}
				queue[Slytherin]--;
				break;
			case 2:
				queue[Ravenclaw]++;
				while (activeJinx == true || queue[Huffelpuff] != 0) {
					wait();
				}
				queue[Ravenclaw]--;
				break;
			case 3:
				queue[Huffelpuff]++;
				while (activeJinx == true) {
					wait();
				}
				queue[Huffelpuff]--;
				break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		activeJinx = true;
	}

	public synchronized void jinxDone() {
		activeJinx = false;
		notifyAll();
	}
}
