package ngdemo.tests.integration.web.infrastructure;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientProvider {
    private final ServerProvider serverProvider;

    public ClientProvider(ServerProvider serverProvider) {
        this.serverProvider = serverProvider;
    }

    public WebResource getWebResource() {
        Client client = Client.create(new DefaultClientConfig());
        return client.resource(serverProvider.getBaseURI());
    }

}
