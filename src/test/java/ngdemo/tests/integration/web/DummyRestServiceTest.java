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

public class DummyRestServiceTest {

    private final ServerProvider serverProvider;
    private final ClientProvider clientProvider;

    public DummyRestServiceTest() {
        serverProvider = new ServerProvider();
        clientProvider = new ClientProvider(serverProvider);
    }

    @Before
    public void startServer() throws IOException {
        serverProvider.createServer();
    }

    @After
    public void stopServer() {
        serverProvider.stop();
    }

    @Test
    public void testGetDefaultUser() throws IOException {
        WebResource service = clientProvider.getWebResource();
        ClientResponse resp = service.path("web").path("dummy")
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
