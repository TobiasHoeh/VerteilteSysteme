import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FileServer extends UnicastRemoteObject implements FileService {
	public FileServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			Naming.rebind("fileService", new FileServer());
			System.out.println("******** FileServer started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String read(String fileName, int lineNo) throws RemoteException {
		File file = new File(fileName);
		String line = null;
		try {
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			int counter = 0;
			while ((line = fileReader.readLine()) != null || counter <= lineNo) {
				counter++;
				if (counter == lineNo) {
					break;
				}
			}
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	@Override
	public boolean write(String fileName, int lineNo, String text)
			throws RemoteException {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File tmpFile = new File(fileName + ".tmp");
		try {
			String line = null;
			int counter = 0;
			BufferedReader fileReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(file)));
			PrintWriter fileWriter = new PrintWriter(tmpFile);
			while ((line = fileReader.readLine()) != null) {
				counter++;
				if (counter != lineNo) {
					fileWriter.println(line);
				} else {
					fileWriter.println(text);
				}
				fileWriter.flush();
			}
			if (counter < lineNo) {
				fileWriter.println(text);
				fileWriter.flush();
			}
			fileWriter.close();
			fileReader.close();
			if (file.delete()) {
				tmpFile.renameTo(file);
				tmpFile.delete();
			}
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
