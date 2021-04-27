package fr.umfds.TPtestServicesREST2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4ClassRunner.class)
public class TestBrainstormTest {

	@Mock
	BrainstormDB brainMock;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	BrainstormDB brainstormDB = new BrainstormDB();
	ArrayList<Brainstorm> listeBrainstorms = new ArrayList<Brainstorm>() {
		{
			// unorder Brainstorm2 before Brainstorm1
			add(new Brainstorm("brainstorm2", 2, new ArrayList<Idea>()));
			add(new Brainstorm("brainstorm1", 1, new ArrayList<Idea>()));
		}
	};

	@Test
	public void addTest() {
		assertEquals(listeBrainstorms.size(), 2);
	}

	@Test
	public void getRessourceTest() {
		Comparator<Brainstorm> NameComparator = new Comparator<Brainstorm>() {

			public int compare(Brainstorm s1, Brainstorm s2) {
				String name1 = s1.getNom().toUpperCase();
				String name2 = s2.getNom().toUpperCase();
				return name1.compareTo(name2);
			}
		};

		try {
			Collections.sort(listeBrainstorms, NameComparator);
			Mockito.when(brainMock.getBrainstorm()).thenReturn(listeBrainstorms);

			assertEquals("brainstorm1", brainstormDB.getBrainstorm().get(0).getNom());

		} catch (Exception e) {
		}
	}

	@Test
	public void getBraistormByIdTest() {
		Mockito.when(brainMock.getBrainstormById(1)).thenReturn(listeBrainstorms.get(0));

		assertEquals(1, brainstormDB.getBrainstormById(1).getIdentifiant());
		assertEquals("brainstorm1", brainstormDB.getBrainstormById(1).getNom());

	}

	@Test
	public void addBrainStorm() {
		Brainstorm b = new Brainstorm("brain", 7, new ArrayList<>(Arrays.asList(new Idea("idea7"))));
		listeBrainstorms.add(b);
		Mockito.when(brainMock.addBrainStorm(b)).thenReturn(true);
		
		assertEquals(true, brainstormDB.addBrainStorm(new Brainstorm()));
		assertEquals(b.getIdentifiant(), listeBrainstorms.stream().filter(bs -> bs.getIdentifiant() == b.getIdentifiant()).findFirst().get().getIdentifiant());
	}
	
	@Test
	public void putBrainStorm() {
		Brainstorm b = null;
		b = listeBrainstorms.stream().filter(brainstorm -> brainstorm.getIdentifiant() == 1).findAny().orElse(null);
		b.getListIdea().add(new Idea("idea"));
		
		assertEquals(1, b.getListIdea().size());
		b.getListIdea().add(new Idea("idea2"));
		assertEquals(2, b.getListIdea().size());

	}

	@Test
	public void getBrainStormIdeas() {
		Brainstorm b = null;
		b = listeBrainstorms.stream().filter(brainstorm -> brainstorm.getIdentifiant() == 1).findAny().orElse(null);
		b.getListIdea().add(new Idea("idea"));
		assertEquals(true, b.getListIdea().get(0).idea.equals("idea"));
		return;

	}
}
