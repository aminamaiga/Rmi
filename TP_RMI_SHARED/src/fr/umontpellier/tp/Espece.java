package fr.umontpellier.tp;
import java.io.Serializable;

public class Espece implements Serializable {
	String name;
	Integer dureeVie;

	public Espece() {
	}

	public Espece(String name, Integer dureeVie) {
		super();
		this.name = name;
		this.dureeVie = dureeVie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDureeVie() {
		return dureeVie;
	}

	public void setDureeVie(Integer dureeVie) {
		this.dureeVie = dureeVie;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", dureeVie=" + dureeVie + "]";
	}

}
