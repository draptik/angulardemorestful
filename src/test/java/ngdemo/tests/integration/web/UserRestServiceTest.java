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
    public void testGetAllUsersShouldReturnSuccessStatusAndCorrectData() throws IOException {
        ClientResponse resp = webService.path("web").path("users")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);
        String actual = resp.getEntity(String.class);

        assertEquals(200, resp.getStatus());

        // {"user":[{"firstName":"Foo1","lastName":"Bar1"},{"firstName":"Foo2","lastName":"Bar2"},{"firstName":"Foo3","lastName":"Bar3"},{"firstName":"Foo4","lastName":"Bar4"},{"firstName":"Foo5","lastName":"Bar5"},{"firstName":"Foo6","lastName":"Bar6"},{"firstName":"Foo7","lastName":"Bar7"},{"firstName":"Foo8","lastName":"Bar8"},{"firstName":"Foo9","lastName":"Bar9"},{"firstName":"Foo10","lastName":"Bar10"}]}

        String expectedUser1 = "{\"firstName\":\"Foo1\",\"lastName\":\"Bar1\"}";
        String expectedUser10 = "{\"firstName\":\"Foo10\",\"lastName\":\"Bar10\"}";

        assertTrue(actual.contains(expectedUser1));
        assertTrue(actual.contains(expectedUser10));
    }
}
