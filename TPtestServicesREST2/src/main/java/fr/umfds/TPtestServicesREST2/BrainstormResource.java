package fr.umfds.TPtestServicesREST2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/brainstorms")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class BrainstormResource {
	@Inject
	public BrainstormDB brainstormDB = new BrainstormDB();

	public BrainstormResource() {

	}

	public BrainstormResource(BrainstormDB brainstormDB) {
		this.brainstormDB = brainstormDB;
	}

	public static Comparator<Brainstorm> NameComparator = new Comparator<Brainstorm>() {
		public int compare(Brainstorm s1, Brainstorm s2) {
			String name1 = s1.getNom().toUpperCase();
			String name2 = s2.getNom().toUpperCase();
			return name1.compareTo(name2);
		}
	};

	@GET
	@Path("/")
	public ArrayList<Brainstorm> getBrainstorms() {
		ArrayList<Brainstorm> listBrainStorm = brainstormDB.getBrainstorm();
		Collections.sort(listBrainStorm, NameComparator);

		return listBrainStorm;
	}

	@GET
	@Path("{id}")
	public Response getBrainstorm(@PathParam("id") Integer id) {
		Brainstorm b = brainstormDB.getBrainstormById(id);
		return b != null ? Response.status(Response.Status.OK).entity(b).build()
				: Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Path("/add")
	public Response addBrainstorm(Brainstorm brainstorm) {
		System.out.println(brainstorm);
		Boolean b = brainstormDB.addBrainStorm(brainstorm);
		return b ? Response.status(Response.Status.OK).entity(b).build()
				: Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("{id}/{idea}")
	public Response putBrainstorm(@PathParam("id") Integer brainstormId, @PathParam("idea") String idea ) {
		Brainstorm b = brainstormDB.putBrainStorm(idea, brainstormId);
		return b != null ? Response.status(Response.Status.OK).entity(b).build()
				: Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
	@Path("/ideas/{id}")
	public List<Idea> getBrainstormIdeas(@PathParam("id") Integer brainstormId) {
		return brainstormDB.getBrainStormIdeas(brainstormId);
	}
}
