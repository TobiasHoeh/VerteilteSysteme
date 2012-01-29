public class Hogwarts {

	public static void main(String[] args) {
		HogwartsMonitor monitor = new HogwartsMonitor();
		Zauber zauber = new Zauber(1, monitor, "#1 Cooler Spruch");
		Thread t = new Thread(zauber);
		t.start();

		zauber = new Zauber(2, monitor, "#2 Neuer Spruch");
		t = new Thread(zauber);
		t.start();

		zauber = new Zauber(1, monitor, "#3 Spruch");
		t = new Thread(zauber);
		t.start();

		zauber = new Zauber(3, monitor, "#4 Cooler Spruch");
		t = new Thread(zauber);
		t.start();

		zauber = new Zauber(0, monitor, "#5 Neuer Spruch");
		t = new Thread(zauber);
		t.start();

		zauber = new Zauber(2, monitor, "#6 Spruch");
		t = new Thread(zauber);
		t.start();

		zauber = new Zauber(3, monitor, "#6 Cooler Spruch");
		t = new Thread(zauber);
		t.start();
	}
}
