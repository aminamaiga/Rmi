package fr.umontpellier.tp;

import java.rmi.Remote;
import java.rmi.RemoteException;
//1 - Le client définit donc une interface Remote que le serveur sera en mesure d'utiliser quand bon lui semble. 

public interface RemoteClientInterface extends Remote {
 public void alert(int seuil) throws RemoteException;
}
