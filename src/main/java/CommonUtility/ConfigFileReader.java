package CommonUtility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigFileReader {

	private Properties properties;
	// Use forward slashes for file paths because it can find the path in win,MAC,Linux all types
    private final static String propertyFilePath = "/src/test/resources/TestData/config.properties"; 

    public ConfigFileReader(){
		FileReader reader;
		try {
			reader = new FileReader(System.getProperty("user.dir") + propertyFilePath);
			properties = new Properties();
			properties.load(reader);
			reader.close();	
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error loading Configuration.Properties from " + propertyFilePath);
		}		
	}

	public String getConfigData(String key) {
		String value = properties.getProperty(key);
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("Key not found in the configuration.properties file: " + key);
		}
	}


}
