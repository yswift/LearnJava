package db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	public static final String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=LearnJava;user=LearnJava;password=LearnJavaPwd";
//	public static final String connectionUrl = "jdbc:sqlserver://10.2.7.39:1433;databaseName=LearnJava;user=LearnJava;password=LearnJavaPwd";
	
	Connection connection;
	
	public void connect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(connectionUrl);
			return;
		} 
	}
	
	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
	
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connect();
		}
		return connection;
	}

}
