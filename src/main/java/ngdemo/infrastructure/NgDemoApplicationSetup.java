package ngdemo.infrastructure;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * This is the entry point for Guice Dependency Injection when called from a servlet container using web.xml
 * <p/>
 * You should have something similar in your web.xml:
 * <p/>
 * <listener>
 * <listener-class>ngdemo.infrastructure.NgDemoApplicationSetup</listener-class>
 * </listener>
 */
public class NgDemoApplicationSetup extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(ModuleFactory.getModules());
    }
}
