import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloService extends UnicastRemoteObject implements Hello {

	protected HelloService() throws RemoteException {
		super();
	}

	@Override
	public String helloWorld() throws RemoteException {
		System.out.println("Hello, this is the Server");
		return "Hallo";

	}

	public static void main(String[] args) {
		try {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new RMISecurityManager());
			}
			HelloService hs = new HelloService();
			Naming.rebind("Hello", hs);
			System.out.println("**Hello Server started**");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
