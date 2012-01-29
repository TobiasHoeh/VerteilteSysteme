import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileService extends Remote {
	public String read(String fileName, int lineNo) throws RemoteException;

	public boolean write(String fileName, int lineNo, String text)
			throws RemoteException;
}
