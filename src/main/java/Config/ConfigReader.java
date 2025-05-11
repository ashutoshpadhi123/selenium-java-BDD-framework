package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static String env;

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/environment.properties");
            properties = new Properties();
            properties.load(file);
            env = System.getProperty("env", "sauceDemo");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load environment.properties file");
        }
    }

    public static String get(String key) {
//        return properties.getProperty(key);
        if (key.equalsIgnoreCase("base.url")) {
            return properties.getProperty(env + ".url");
        }
        return properties.getProperty(key);
    }
}
