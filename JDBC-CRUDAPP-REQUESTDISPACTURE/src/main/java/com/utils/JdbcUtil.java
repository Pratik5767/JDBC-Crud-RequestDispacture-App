package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
		return logicalConnection();
		// return physicalConnection();
	}

	private static Connection logicalConnection() throws SQLException {
		String fileLoc = "D:\\Pratik\\git\\Jdbc-Crud-RequestDispacture-App\\JDBC-CRUDAPP-REQUESTDISPACTURE\\src\\main\\java\\com\\properties\\application.properties";
		HikariConfig config = new HikariConfig(fileLoc);
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}
	
	@SuppressWarnings("unused")
	private static Connection physicalConnection() throws FileNotFoundException, IOException, SQLException {
		FileInputStream fis = new FileInputStream(
				"D:\\Pratik\\git\\Jdbc-Crud-RequestDispacture-App\\JDBC-CRUDAPP-REQUESTDISPACTURE\\src\\main\\java\\com\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		Connection connection = DriverManager.getConnection(properties.getProperty("jdbcUrl"),
				properties.getProperty("user"), properties.getProperty("password"));
		return connection;
	}
}