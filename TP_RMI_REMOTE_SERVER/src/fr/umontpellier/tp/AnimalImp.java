package fr.umontpellier.tp;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AnimalImp extends UnicastRemoteObject implements IAnimal {

	String name;
	String masterName;
	String race;
	DossierImpl dossier;
	Espece espece;

	protected AnimalImp() throws RemoteException {
		this.dossier = new DossierImpl();
	}

	public AnimalImp(String name, String masterName, String race, DossierImpl dossier, Espece espece)
			throws RemoteException {
		super();
		this.name = name;
		this.masterName = masterName;
		this.race = race;
		this.dossier = dossier;
		this.espece = espece;
	}


	@Override
	public String getAnimalName() throws RemoteException {
		//
		return this.name;
	}

	@Override
	public String getMasterName() throws RemoteException {
		return this.masterName;
	}

	@Override
	public String getRace() throws RemoteException {
		return this.getRace();
	}

	@Override
	public void setAnimalName(String nom) {
		this.name = nom;
	}

	@Override
	public void setMasterName(String nomMaitre) {
		this.masterName = nomMaitre;
	}

	@Override
	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public DossierImpl getDossier() {
		return this.dossier;
	}

	@Override
	public void setDossierName(String doss) throws RemoteException {
		this.dossier.setDossier(doss);
	}

	@Override
	public String toStringBis() {
		String toStrg = "Animal [nom=" + name + ", nomMaitre=" + masterName + ", race=" + race + ", dossier="
				+ dossier.getDossier() + " , espece=" + espece.toString() + "]" + "\n";
		System.out.print(toStrg);
		return toStrg;
	}

	public Espece getEspece() {
		return espece;
	}

	@Override
	public void setEspece(Espece espece) throws RemoteException {
		this.espece = espece;
	}

}
