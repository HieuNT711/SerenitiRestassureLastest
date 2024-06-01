package example;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class PropertiesManager {
    public static final EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();

    PropertiesManager() {
        throw new IllegalStateException("Properties Manager class");
    }

    public static String getEnvironmentSpecFromProperty(String propertyName) {
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(propertyName);
    }

}

