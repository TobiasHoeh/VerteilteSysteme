import java.rmi.Naming;

public class HelloClient {
	public static void main(String[] args) {
		try {
			Hello h = (Hello) Naming.lookup("//localhost/Hello");
			System.out.println(h.helloWorld());
			System.out.println("** Service called successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
