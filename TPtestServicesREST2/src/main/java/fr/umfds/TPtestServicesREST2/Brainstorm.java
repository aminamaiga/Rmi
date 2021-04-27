package fr.umfds.TPtestServicesREST2;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "brainstorm")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Brainstorm {
	private String nom;
	private Integer identifiant;
	private ArrayList<Idea> listIdea = new ArrayList<>();

	public Brainstorm() {

	}

	public Brainstorm(String nom, Integer identifiant, ArrayList<Idea> listIdea) {
		super();
		this.nom = nom;
		this.identifiant = identifiant;
	    this.listIdea = listIdea;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}

	public List<Idea> getListIdea() {
		return listIdea;
	}

	public void setListIdea(ArrayList<Idea> listIdea) {
		this.listIdea = listIdea;
	}

}
