package ngdemo.tests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import ngdemo.repositories.contract.UserRepository;
import ngdemo.repositories.impl.UserRepositoryImpl;
import ngdemo.service.contract.UserService;
import ngdemo.service.impl.UserServiceImpl;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

import static junit.framework.Assert.assertEquals;

public class UserRestServiceTest {

    static final URI BASE_URI = getBaseURI();
    HttpServer server;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(9998).build();
    }

    @Before
    public void startServer() throws IOException {
        System.out.println("Starting grizzly...");

        Injector injector = Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(UserService.class).to(UserServiceImpl.class);
                bind(UserRepository.class).to(UserRepositoryImpl.class);
            }
        });

        ResourceConfig rc = new PackagesResourceConfig("ngdemo.rest");
        IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
        server = GrizzlyServerFactory.createHttpServer(BASE_URI + "rest/", rc, ioc);

        System.out.println(String.format("Jersey app started with WADL available at "
                + "%srest/application.wadl\nTry out %sngdemo\nHit enter to stop it...",
                BASE_URI, BASE_URI));
    }

    @After
    public void stopServer() {
        server.stop();
    }

    @Test
    public void testGetDefaultUser() throws IOException {
        Client client = Client.create(new DefaultClientConfig());
        WebResource service = client.resource(getBaseURI());
        ClientResponse resp = service.path("rest").path("users")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);
        String text = resp.getEntity(String.class);

        assertEquals(200, resp.getStatus());
        assertEquals("{\"firstName\":\"JonFromREST\",\"lastName\":\"DoeFromREST\"}", text);
    }

//    @Ignore
//    public static void main(String[] args) throws Exception {
//        UserRestServiceTest test = new UserRestServiceTest();
//        test.startServer();
//        //noinspection ResultOfMethodCallIgnored
//        System.in.read(); // hit enter to stop the server
//        test.server.stop();
//    }
}
