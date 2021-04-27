package fr.umontpellier.tp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CabinetImpl extends UnicastRemoteObject implements Icabinet {
	List<IAnimal> listPatient = new ArrayList<>();
	List<RemoteClientInterface> listClient = new ArrayList<>();
	public static final int SEUIL1 = 100;
	public static final int SEUIL2 = 500;
	public static final int SEUIL3 = 1000;

	protected CabinetImpl() throws RemoteException {
		super();
	}

	@Override
	public void addPatient(IAnimal animal) {
		try {
			listPatient.add(animal);
			notifyAllClients();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IAnimal findPatient(String name) {
		try {
			for (IAnimal a : listPatient) {
				if (a.getAnimalName().equals(name)) {
					return a;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String printAllAnimal() {
		String s = "";
		try {
			for (IAnimal a : listPatient) {
				s += a.toStringBis();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return s;
	}

	@Override
	public void registerClient(RemoteClientInterface client) {
		listClient.add(client);
		System.out.println("Un nouveau Client vient de se souscrire.");
	}

	@Override
	public void notifyAllClients() {
		try {
			for (RemoteClientInterface remoteClient : listClient) {
				int size = listPatient.size();
				if (size == SEUIL1 || size == SEUIL2 || size == SEUIL3) {

					remoteClient.alert(size);
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void unRegisterClient(RemoteClientInterface client) throws RemoteException {
		System.out.println("Un Client vient de se désabonner.");
		listClient.remove(client);
	}

}
