package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadConfig {
	String propValue = "";
	InputStream inputStream = null;
	String propFileName = "set.properties";
	
	static final Logger logger = Logger.getLogger(ReadConfig.class);
	
	
	public String readPropValues(String propName) {

		try {
			Properties prop = new Properties();

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
			}
			propValue = prop.getProperty(propName);

		} catch (Exception e) {
			logger.error("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return propValue;
	}

	public static String getPropValue(String propName){
		ReadConfig con = new ReadConfig();
		return con.readPropValues(propName);

	}


}
