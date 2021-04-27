package fr.umontpellier.tp;

import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	//path par défaut à modifier si vous rencontrez des problémes de workspace
	static final String CODEBASE_PATH = "file:C:/Users/HP/eclipse-workspace/TP_RMI_REMOTE_CLIENT/bin/";
	static final String POLICY_PATH = new File("policy").getAbsolutePath();

	public Server() {
	}

	public static void main(String args[]) {

		try {
			System.setProperty("java.security.policy", POLICY_PATH);
			System.setProperty("java.rmi.server.codebase", CODEBASE_PATH);

			System.out.println("Mise en place du Security Manager ...");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			AnimalImp objAnimal = new AnimalImp();
			CabinetImpl objCabinet = new CabinetImpl();
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry = LocateRegistry.getRegistry();
			if (registry == null) {
				System.err.println("RmiRegistry not found");
			} else {
				System.err.println("Server ready");

				// create aminal object
				objAnimal = new AnimalImp("Chat", "Jean", "Persan", new DossierImpl("Dossier Chat"),
						new Espece("Chat de France ", 5));
				registry.rebind("Animal", objAnimal);

				// cabinet object
				registry.rebind("Cabinet", objCabinet);

			}
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}