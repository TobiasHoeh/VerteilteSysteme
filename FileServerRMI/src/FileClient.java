import java.rmi.Naming;

public class FileClient {
	public static void main(String[] args) {
		try {
			FileService h = (FileService) Naming
					.lookup("//localhost/fileService");
			h.write("test.txt", 1, "SuperGuterText");
			System.out.println(h.read("test.txt", 1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
