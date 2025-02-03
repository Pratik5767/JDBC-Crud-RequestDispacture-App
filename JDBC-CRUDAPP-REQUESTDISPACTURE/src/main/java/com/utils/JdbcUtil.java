package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	// so that no object should be created
	private JdbcUtil() {
	};

	// loading and register the class
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// Establish the connection
	public static Connection getJdbcConnection() throws SQLException, IOException {
		FileInputStream fis = new FileInputStream(
				"D:\\Pratik\\git\\JDBC-CRUD-WEB-APP\\JDBCCRUDWEBAPP\\src\\main\\java\\com\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		Connection connection = DriverManager.getConnection(properties.getProperty("jdbcUrl"),
				properties.getProperty("user"), properties.getProperty("password"));
		return connection;

	}
}