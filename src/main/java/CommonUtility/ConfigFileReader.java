package CommonUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigFileReader {

	private static Properties properties;
	//private final static String propertyFilePath = "\\TestData\\config.properties";
    private final static String propertyFilePath = "/src/test/resources/TestData/config.properties"; // Use forward slashes for file paths

	static {
		properties = new Properties();
		try {
			FileInputStream file = new FileInputStream(System.getProperty("user.dir") + propertyFilePath);
			properties.load(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading Configuration.Properties from " + propertyFilePath);
		}
	}

	public static String getConfigData(String key) {
		String value = properties.getProperty(key);
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("Key not found in the configuration.properties file: " + key);
		}
	}


}
