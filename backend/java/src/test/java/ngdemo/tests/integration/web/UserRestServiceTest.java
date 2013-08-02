package ngdemo.tests.integration.web;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import ngdemo.domain.User;
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

    @Test
    public void testGetUserByIdShouldReturnSuccessStatus() throws IOException {
        ClientResponse resp = webService.path("web").path("users/1")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);

        assertEquals(200, resp.getStatus());
    }

    @Test
    public void testGetUserByIdOneShouldReturnFirstUser() throws IOException {
        ClientResponse resp = webService.path("web").path("users/1")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        System.out.println("Got stuff: " + resp);
        String actual = resp.getEntity(String.class);
        String expectedUser1 = "{\"id\":1,\"firstName\":\"Foo1\",\"lastName\":\"Bar1\"}";

        assertTrue(actual.equals(expectedUser1));
    }

    @Test
    public void testCreateUserShouldReturnNewUserWithCorrectId() throws IOException {
        /*
              $ curl -i -X POST -H 'Content-Type: application/json' -d '{"id":0, "firstName":"XX", "lastName":"YY"}' http://localhost:8080/ngdemo/web/users

                HTTP/1.1 200 OK
                Server: Apache-Coyote/1.1
                Content-Type: application/json
                Transfer-Encoding: chunked
                Date: Mon, 22 Jul 2013 09:12:38 GMT

                {"id":12,"firstName":"XX","lastName":"YY"}
         */

        ClientResponse resp = webService.path("web").path("users")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, new User());

        System.out.println("Got stuff: " + resp);
        String actual = resp.getEntity(String.class);
        String expectedId = "\"id\":11";

        assertTrue(actual.contains(expectedId));
    }

    @Test
    public void testUpdateUserShouldReturnUpdatedUser() throws IOException {

        User updateUser = new User();
        updateUser.setId(1);
        updateUser.setFirstName("XX");
        updateUser.setLastName("YY");

        ClientResponse resp = webService.path("web").path("users/1")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, updateUser);

        System.out.println("Got stuff: " + resp);
        String actual = resp.getEntity(String.class);
        String expectedId = "\"id\":1";
        String expectedFirstNam = "\"firstName\":\"XX\"";
        String expectedLastNam = "\"lastName\":\"YY\"";

        assertTrue(actual.contains(expectedId));
        assertTrue(actual.contains(expectedFirstNam));
        assertTrue(actual.contains(expectedLastNam));
    }

    @Test
    public void testGetNumberOfUserShouldReturnSuccessStatusAndCorrectNumber() throws IOException {

        String actual = getNumberOfUsers();

        String expectedNumberOfUsers = "10";
        assertTrue(actual.equals(expectedNumberOfUsers));
    }

    private String getNumberOfUsers() {
        ClientResponse resp = webService.path("web").path("users/numberOfUsers")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        System.out.println("Got stuff: " + resp);
        assertEquals(200, resp.getStatus());
        return resp.getEntity(String.class);
    }

    @Test
    public void testRemoveUserShouldReturnSuccessStatus() throws IOException {

        ClientResponse resp = webService.path("web").path("users/1")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);

        System.out.println("Got stuff: " + resp);
        assertEquals(204, resp.getStatus());  // 204: no content
    }

    @Test
    public void testRemoveUserShouldDecreaseNumberOfUsersByOne() throws IOException {

        int numberOfUsersBefore = Integer.parseInt(getNumberOfUsers());

        webService.path("web").path("users/1")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);

        int numberOfUsersAfter = Integer.parseInt(getNumberOfUsers());

        assertTrue(numberOfUsersAfter == numberOfUsersBefore - 1);
    }
}
