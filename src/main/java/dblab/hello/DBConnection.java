package dblab.hello;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection(String url, String username, String password) throws SQLException {
		Connection connection = DriverManager.getConnection(url, username,password);
		System.err.println("The connection is successfully obtained");
		return connection;
	}
}