package fr.umfds.TPtestServicesREST2;

import java.net.URI;
import java.util.logging.Level;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.Binding;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.UriBuilder;

@ApplicationPath("/api")
public class BrainstormingAppLauncher extends ResourceConfig {
	public static final URI BASE_URI = getBaseURI();
	private static final int PORT = 8082;

	private static URI getBaseURI() {
		// ici choisissez l'adresse à laquelle vous rendez accessible votre API, ainsi
		// que le numéro de port
		return UriBuilder.fromUri("http://localhost/api/").port(PORT).build();
	}

	public static void main(String[] args) {

		ResourceConfig rc = new ResourceConfig();
		rc.registerClasses(BrainstormResource.class);
		rc.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.WARNING.getName());

		try {
			rc.register(new AbstractBinder() {
				@Override
				protected void configure() {
					BrainstormDB bs = new BrainstormDB();
					bind(bs ).to(BrainstormDB.class);
				}
			});
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
			server.start();

			System.out.println(String.format(
					"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
					BASE_URI, BASE_URI));

			System.in.read();
			server.shutdownNow();
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}