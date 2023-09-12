package com.advocate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/ADVOCATEDB";
	private static final String Customer_NAME = "root";
	private static final String PASSWORD = "Czsubham@123";

	private static Connection connection;

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, Customer_NAME, PASSWORD);
//			System.out.println("Connection Established");
		} catch (SQLException e) {
			System.err.println("Error : Connection Not Established\n" + e);
		}
		return connection;

	}
}