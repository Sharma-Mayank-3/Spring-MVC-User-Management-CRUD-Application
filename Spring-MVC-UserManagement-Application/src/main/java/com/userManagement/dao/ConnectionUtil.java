package com.userManagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class ConnectionUtil {
	
	private final String DriverURL = "com.mysql.cj.jdbc.Driver";
	private final String ConnectionURL = "jdbc:mysql://localhost/userDB";
	
	private final String DBUserName = "root";
	private final String DBPassword = "root";
	
	public Connection getConnection() throws ClassNotFoundException {
		Class.forName(DriverURL);
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(ConnectionURL,DBUserName, DBPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
}
