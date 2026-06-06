package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReaderManager {

	private ConfigReaderManager() // prevents object creation for this class..
	{
	}

	private static Properties properties = new Properties();

	static { // executed only once when class is loaded into the memory..

		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "configs" + File.separator + "config.qa.properties");

		FileReader fileReader = null; // since cannot use without initialization in try block..
		try {
			fileReader = new FileReader(configFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("incorrect file path...." + e.getMessage());
		} catch (IOException e) {
			System.out.println("incorrect input passed...." + e.getMessage());
		}

	}

	public static String getProperties(String keyName)

	{
		return properties.getProperty(keyName); // keyName passed here would come in string format from calling
												// method..returns String value
	}
}
