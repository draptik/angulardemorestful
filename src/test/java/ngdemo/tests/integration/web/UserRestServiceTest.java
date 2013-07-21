package ngdemo.tests.integration.web;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import ngdemo.tests.integration.web.infrastructure.ClientProvider;
import ngdemo.tests.integration.web.infrastructure.ServerProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class UserRestServiceTest {

    private final ServerProvider serverProvider;
    private final ClientProvider clientProvider;
    private WebResource webService;

    public UserRestServiceTest() {
        serverProvider = new ServerProvider();
        clientProvider = new ClientProvider(serverProvider);
    }

    @Before
    public void startServer() throws IOException {
        serverProvider.createServer();
        webService = clientProvider.getWebResource();
    }

    @After
    public void stopServer() {
        serverProvider.stop();
    }


    @Test
    public void testGetAllUsersShouldReturnSuccessStatus() throws IOException {
        ClientResponse resp = webService.path("web").path("users")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);

        assertEquals(200, resp.getStatus());
    }

    @Test
    public void testGetAllUsersShouldReturnJSArray() throws IOException {
        ClientResponse resp = webService.path("web").path("users")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);
        String actual = resp.getEntity(String.class);

        assertTrue("Result must be a JavaScript array: But it starts with '{'!", !actual.startsWith("{"));
        assertTrue("Result must be a JavaScript array: But it does not start with '['!", actual.startsWith("["));
    }

    @Test
    public void testGetAllUsersShouldReturnUsers() throws IOException {
        ClientResponse resp = webService.path("web").path("users")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);
        String actual = resp.getEntity(String.class);

        //[{"id":1,"firstName":"Foo1","lastName":"Bar1"},{"id":2,"firstName":"Foo2","lastName":"Bar2"},{"id":3,"firstName":"Foo3","lastName":"Bar3"},{"id":4,"firstName":"Foo4","lastName":"Bar4"},{"id":5,"firstName":"Foo5","lastName":"Bar5"},{"id":6,"firstName":"Foo6","lastName":"Bar6"},{"id":7,"firstName":"Foo7","lastName":"Bar7"},{"id":8,"firstName":"Foo8","lastName":"Bar8"},{"id":9,"firstName":"Foo9","lastName":"Bar9"},{"id":10,"firstName":"Foo10","lastName":"Bar10"}]
        String expectedUser1 = "{\"id\":1,\"firstName\":\"Foo1\",\"lastName\":\"Bar1\"}";
        String expectedUser10 = "{\"id\":10,\"firstName\":\"Foo10\",\"lastName\":\"Bar10\"}";

        assertTrue(actual.contains(expectedUser1));
        assertTrue(actual.contains(expectedUser10));
    }
}
