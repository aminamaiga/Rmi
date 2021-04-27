package fr.umontpellier.tp;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossier extends Remote {
	String getDossier() throws RemoteException;

	String setDossier(String name) throws RemoteException;
}
