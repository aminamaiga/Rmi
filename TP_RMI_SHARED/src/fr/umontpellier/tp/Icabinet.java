package fr.umontpellier.tp;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Icabinet extends Remote {
	void addPatient(IAnimal animal) throws RemoteException;

	IAnimal findPatient(String name) throws RemoteException;

	String printAllAnimal() throws RemoteException;
	
	void registerClient(RemoteClientInterface client) throws RemoteException;
	
	void unRegisterClient(RemoteClientInterface client) throws RemoteException;
	
	void notifyAllClients() throws RemoteException;
	
}
