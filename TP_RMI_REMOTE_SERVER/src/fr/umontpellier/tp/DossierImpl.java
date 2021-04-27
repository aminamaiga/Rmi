package fr.umontpellier.tp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DossierImpl extends UnicastRemoteObject implements IDossier {

	String name;

	protected DossierImpl() throws RemoteException {
		super();
	}

	public DossierImpl(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public String getDossier() {
		return name;
	}

	@Override
	public String setDossier(String name) {
		this.name = name;
		return name;
	}

}
