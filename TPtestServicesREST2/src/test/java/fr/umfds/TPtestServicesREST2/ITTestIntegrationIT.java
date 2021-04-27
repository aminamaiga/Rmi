package fr.umfds.TPtestServicesREST2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.DefaultLenientStubber;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ITTestIntegrationIT extends JerseyTest {

	@BeforeClass
	public static void avantTests() {
		System.out.println("------------------------");
		System.out.println("Avant Tests");
		System.out.println("------------------------");
	}

	@Mock()
	public BrainstormDB dbMock;
	ArrayList<Brainstorm> l = new ArrayList<Brainstorm>(
			Arrays.asList(new Brainstorm("Brainstorm2", 2, new ArrayList<Idea>(Arrays.asList(new Idea("idea2")))),
					new Brainstorm("Brainstorm1", 1, null), new Brainstorm("Brainstorm2", 3, null),
					new Brainstorm("Brainstorm1", 4, null)));
	Brainstorm brainstorm = new Brainstorm("newAddedBrainStorm", 5, null);

	@Override
	protected Application configure() {

		ResourceConfig resourceConfig = new ResourceConfig(BrainstormResource.class);
		resourceConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.WARNING.getName());

		resourceConfig.register(new AbstractBinder() {
			@Override
			protected void configure() {
				Mockito.when(dbMock.getBrainstorm()).thenReturn(l);

				// Mockito.when(dbMock.addBrainStorm(brainstorm)).thenReturn(true);

				Mockito.when(dbMock.getBrainstormById(1)).thenReturn(l.get(1));

				bind(dbMock).to(BrainstormDB.class);
			}
		});

		return resourceConfig;
	}

	@Test
	public void testGetBrainstorms() {

		Response response = target("/brainstorms").request(MediaType.APPLICATION_JSON_TYPE).get();
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		ArrayList<Brainstorm> readEntities = response.readEntity(new GenericType<ArrayList<Brainstorm>>() {

		});
		Assert.assertNotNull(readEntities);
		Assert.assertEquals(4, readEntities.size());
		Assert.assertTrue(readEntities.stream().anyMatch(current -> current.getIdentifiant() == 1));
	}

	@Test
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void testAddBrainstorms() {

		Response response = target("/brainstorms/add").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(new Brainstorm("newAddedBrainStorm", 5, null), MediaType.APPLICATION_JSON));
		Assert.assertEquals("Http Response should be 200: ", Status.NOT_FOUND.getStatusCode(), response.getStatus());
		Boolean readEntities = response.readEntity(new GenericType<Boolean>() {
		});
	}

	@Test
	public void TestgetBrainstormIdeas() {
		Mockito.when(dbMock.getBrainStormIdeas(2)).thenReturn(l.get(0).getListIdea());

		// When 200 CODE;
		Response response = target("/brainstorms/ideas/2").request(MediaType.APPLICATION_JSON_TYPE).get();
		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		ArrayList<Idea> readEntities = response.readEntity(new GenericType<ArrayList<Idea>>() {

		});
		Assert.assertNotNull(readEntities);
		Assert.assertEquals(1, readEntities.size());
	}

	@Test
	public void testGetBrainstormsById() {

		Response response = target("/brainstorms/1").request(MediaType.APPLICATION_JSON_TYPE).get();
		// Then
		Assert.assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());

		// When 404 CODE; id = 9 not exist
		Response response2 = target("/brainstorms/9").request(MediaType.APPLICATION_JSON_TYPE).get();
		// Then
		Assert.assertEquals("Http Response should be 404: ", Status.NOT_FOUND.getStatusCode(), response2.getStatus());

	}
}
