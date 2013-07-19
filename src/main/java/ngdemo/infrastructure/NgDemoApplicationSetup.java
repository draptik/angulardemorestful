package ngdemo.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class NgDemoApplicationSetup extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {

                super.configureServlets();

                // Configuring Jersey via Guice:
                ResourceConfig resourceConfig = new PackagesResourceConfig("ngdemo/web");
                for (Class<?> resource : resourceConfig.getClasses()) {
                    bind(resource);
                }
                serve("/web/*").with(GuiceContainer.class);
            }
        }, new UserModule());
    }
}
