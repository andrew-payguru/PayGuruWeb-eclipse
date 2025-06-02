package za.co.payguru.util;

import java.sql.Connection;
import java.sql.DriverManager;

import za.co.payguru.servlet.WebController;

public class DBUtil {
	private final static String url = "jdbc:postgresql://127.0.0.1/";

	public static Connection getConnection(String dbName, String user, String password) {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url+dbName, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}
	
	public static boolean closeConnection(Connection connection) {
		boolean closed = false;
		try {
			if(connection!=null)
				connection.close();
		}catch (Exception e) {
			System.out.println("Error closing connection: " + e.toString());
		}
		
		return closed;
	}
}

