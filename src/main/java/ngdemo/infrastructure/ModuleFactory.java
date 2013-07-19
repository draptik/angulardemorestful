package ngdemo.infrastructure;

import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.Arrays;
import java.util.List;

public class ModuleFactory {

    public static Module[] getModules() {
        Module[] servletModules = {getServletModule()};
        List<Module> modules = Arrays.asList(servletModules);

        addProjectModules(modules);

        return modules.toArray(new Module[modules.size()]);
    }

    private static void addProjectModules(List<Module> listOfModules) {

        listOfModules.add(new UserModule());

        // Add new modules here...

    }

    private static ServletModule getServletModule() {
        return new ServletModule() {

            @Override
            protected void configureServlets() {

                super.configureServlets();

                // Configuring Jersey via Guice:
                ResourceConfig resourceConfig = new PackagesResourceConfig("ngdemo/rest");
                for (Class<?> resource : resourceConfig.getClasses()) {
                    bind(resource);
                }
                serve("/rest/*").with(GuiceContainer.class);
            }
        };
    }
}
