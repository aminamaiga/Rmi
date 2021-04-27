package fr.umontpellier.tp;

import java.io.File;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientRemote extends UnicastRemoteObject implements RemoteClientInterface {
	//path par défaut à changer si vous rencontrez des problémes de workspace
		static final String POLICY_PATH = new File("policy").getAbsolutePath();

	protected ClientRemote() throws RemoteException {
		super();
	}

	// 1 Une première version simple
	public static void part1(Registry registry) throws RemoteException, NotBoundException {
		IAnimal stub = (IAnimal) registry.lookup("Animal");

		// Q1- get animal name and master name
		String response = stub.getAnimalName() + " " + stub.getMasterName();
		System.out.println("response: " + response);
		// print animal
		System.out.println(stub.toStringBis());
		// set dossier
		stub.setDossierName("Dossier chat from client");
		// update espece only client side
		Espece espece = stub.getEspece();
		espece.setName("Espece Update");
		espece.setDureeVie(4);
		System.out.println(espece);// print espece in client
		stub.toStringBis();// print espece in server (espece don't change in server side)
	}

	// Q2 Classe CabinetVeterinaire
	//  Création de patient
	public static IAnimal createAnimal(IAnimal stub) throws RemoteException {
		Espece espece = new Espece();

		Scanner sc2 = new Scanner(System.in);
		System.out.println("Saisir le nom de l'animal");
		String name = sc2.nextLine();
		System.out.println("Saisir le maitre de l'animal");
		String master = sc2.nextLine();
		System.out.println("Saisir la race de l'animal");
		String race = sc2.nextLine();
		System.out.println("Saisir l'espece de l'animal");
		String especeName = sc2.nextLine();
		System.out.println("Saisir le dossier de l'animal");
		String dossier = sc2.nextLine();
		System.out.println("Saisir la durée de vie l'animal (un entier)");
		Integer durreVie = sc2.nextInt();

		stub.setAnimalName(name);
		stub.setMasterName(master);
		stub.setRace(race);
		stub.setDossierName(dossier);

		espece.setName(especeName);
		espece.setDureeVie(durreVie);
		stub.setEspece(espece);

		return stub;
	}
//Q4 find animal by name
	public static IAnimal findAnimal(Icabinet stub2) throws RemoteException {

		Scanner sc3 = new Scanner(System.in);
		System.out.println("Saisir le nom de l'animal");
		String name = sc3.nextLine();
		IAnimal animal = stub2.findPatient(name);
		return animal;
	}

	// 2 Classe CabinetVeterinaire
    // 3 Création de patient

	public static void part2(Registry registry, IAnimal animal, Icabinet stub2, RemoteClientInterface client) throws AccessException, RemoteException, NotBoundException {
		System.out.println("\nPARTIE 2 DU TP \n");
		
		int option = 1;
		Scanner sc = new Scanner(System.in);

		while (option > 0 && option < 5) {
			System.out.println(
					"\n **  Les opérations possibles sont: \n1- Ajouter un patient. ** ");
			System.out.println("2- Recherche un Animal par son nom.");
			System.out.println("3- Afficher les patients");
			System.out.println("4- Se désabonner");

			option = sc.nextInt();
			switch (option) {
			case 1:
				stub2.addPatient(createAnimal(animal));
				break;
			case 2:
				IAnimal a = findAnimal(stub2);
				if (a != null) {
					System.out.println(a.toStringBis());
				} else {
					System.out.println("Animal non trouvé");
				}
				   break;
			case 3:
				System.out.println(stub2.printAllAnimal());
					break;
			case 4:
				stub2.unRegisterClient(client);
					break;
			}
		}
	}
	
     //Partie 4: Téléchargement de codes
	public static void part4(IAnimal stub) throws RemoteException {
		//SubEspece instance of Espece ; code download
		stub.setEspece(new SubEspece("Chien", 2));
	}
	
	//Partie 5: Alert
	public static void part5(Icabinet stub2, RemoteClientInterface client) throws RemoteException {
		stub2.registerClient(client);
	}
	
	
	public static void main(String[] args) {

		String host = (args.length < 1) ? null : args[0];
		try {
			// new Client 
			RemoteClientInterface client = new ClientRemote();

			System.setProperty("java.security.policy", POLICY_PATH);

			System.out.println("Mise en place du Security Manager ...");
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			Registry registry = LocateRegistry.getRegistry(host);
			
			IAnimal stub = (IAnimal) registry.lookup("Animal");
			Icabinet stub2 = (Icabinet) registry.lookup("Cabinet");
            
			//Call differents methodes
			part1(registry);
			part4(stub);
			part5(stub2, client);
			part2(registry,  stub, stub2, client);

		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	//alert
	@Override
	public void alert(int seuil) throws RemoteException {
		System.out.println("!!! Le seuil " + seuil + " vient d'être franchi");
	}
}
