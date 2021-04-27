package fr.umfds.TPtestServicesREST2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrainstormDB {

	public BrainstormDB() {

	}

	public static ArrayList<Brainstorm> listeBrainstorms = new ArrayList<>(
			Arrays.asList(new Brainstorm("brainstorm1", 1, new ArrayList<>(Arrays.asList(new Idea("idea1")))), new Brainstorm("brainstorm2", 2, null),
					new Brainstorm("brainstorm4", 4, null), new Brainstorm("brainstorm3", 3, null)));

	public ArrayList<Brainstorm> getBrainstorm() {
		return listeBrainstorms;
	}

	public Boolean addBrainStorm(Brainstorm brainstorm) {
		return listeBrainstorms.add(brainstorm);
	}

	public Brainstorm getBrainstormById(Integer id) {
		return listeBrainstorms.stream().filter(brains -> brains.getIdentifiant() == id).findAny().orElse(null);
	}
	
	public Brainstorm putBrainStorm(String idea, Integer id) {
		Brainstorm b = null;
		b = listeBrainstorms.stream().filter(brainstorm -> brainstorm.getIdentifiant() == id).findAny().orElse(null);
		if (b != null) {
			b.getListIdea().add(new Idea(idea));
		}
		
		return b;

	}
	
	public List<Idea> getBrainStormIdeas(Integer id) {
		Brainstorm b = null;
		b = listeBrainstorms.stream().filter(brainstorm -> brainstorm.getIdentifiant() == id).findAny().orElse(null);
		return b.getListIdea();

	}

}
