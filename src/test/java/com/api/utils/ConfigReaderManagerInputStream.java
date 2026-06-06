package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReaderManagerInputStream {

	private ConfigReaderManagerInputStream() // prevents object creation for this class..
	{
	}

	private static Properties properties = new Properties();
	private static String filePath;
	private static String environmentPassed;

	static { // executed only once when class is loaded into the memory..
		
		environmentPassed = System.getProperty("env","qa");		//takes value from CLI, if not passed , default it as qa..
		
		switch(environmentPassed.toLowerCase().trim())
		{
		case "dev" : filePath = "configs" + File.separator + "config.dev.properties";
						break;
		case "qa"  : filePath = "configs" + File.separator + "config.qa.properties";
						break;
		case "uat"  : filePath = "configs" + File.separator + "config.uat.properties";
						break;
		default  :  throw new RuntimeException("Incorrect Environment variable passed...please pass only QA,UAT,DEV...")  ;
						//break;
		}

		// Note:- use below optimized code to explain in interview using InputStream as
		// an argument to load() method..
		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(filePath);
		
		if(inputStream == null)
		{
			 throw new RuntimeException("Incorrect file path passed...unable to find file at location :- "+filePath);
		}

		try {
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("incorrect input passed...." + e.getMessage());
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
