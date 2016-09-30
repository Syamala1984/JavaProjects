/**
 * 
 */
package com.online.notepad.test.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Syamu
 * 
 */
public final class DataSourceConfig {

	public static DriverManagerDataSource getDataSource() throws IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		Properties prop = new Properties();// = getProperties();

		prop.load(DataSourceConfig.class.getClassLoader().getResourceAsStream(
				"database.properties"));

		System.out.println("====prop======" + prop.toString());

		dataSource.setDriverClassName(prop.getProperty("database.driver"));

		dataSource.setUrl(prop.getProperty("database.url"));

		dataSource.setUsername(prop.getProperty("database.user"));

		dataSource.setPassword(prop.getProperty("database.password"));
		
		return dataSource;
	}

}
