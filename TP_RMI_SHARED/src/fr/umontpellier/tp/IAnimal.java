package fr.umontpellier.tp;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
	String getAnimalName() throws RemoteException;

	String getMasterName() throws RemoteException;

	String getRace() throws RemoteException;

	void setAnimalName(String name) throws RemoteException;

	void setMasterName(String master) throws RemoteException;

	void setRace(String race) throws RemoteException;

	IDossier getDossier() throws RemoteException;

	void setDossierName(String dossier) throws RemoteException;

	public void setEspece(Espece espece) throws RemoteException;

	Espece getEspece() throws RemoteException;

	public String toStringBis() throws RemoteException;
}
