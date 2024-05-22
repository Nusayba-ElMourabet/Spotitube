package nl.nusayba.oose.util;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }


    public String getUrl() {
        return properties.getProperty("database.url");
    }

    public String getUsername() {
        return properties.getProperty("database.username");
    }

    public String getPassword() {
        return properties.getProperty("database.password");
    }
    public String connectionString()
    {
        return properties.getProperty("connectionString");
    }
}
