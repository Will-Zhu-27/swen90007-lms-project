package datasource;

import java.net.*;
import java.sql.*;

public class DBConnection {
	// JDBC driver name and database URL
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/Sample";

	// Database credential
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "effort";

	private static final boolean LOCAL_ENV = true;

	static Connection dbConnection = null;

	public static PreparedStatement prepare(String stm) throws SQLException, URISyntaxException {
		PreparedStatement preparedStatement = null;
		try {
			Connection dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(stm);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return preparedStatement;
	}

	public static PreparedStatement prepareInsert(String stm) throws SQLException, URISyntaxException {
		PreparedStatement preparedStatement = null;
		try {
			Connection dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return preparedStatement;
	}

	public static Connection getDBConnection() throws URISyntaxException {
		try {
			if (LOCAL_ENV) {
				Class.forName("org.postgresql.Driver");
				dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				System.out.println("Connected to the PostgreSQL server successfully.");
			} else {
				DriverManager.registerDriver(new org.postgresql.Driver());
				String dbUrl = System.getenv("JDBC_DATABASE_URL");
				dbConnection = DriverManager.getConnection(dbUrl);
				System.out.println("Connected to the cloud PostgreSQL server successfully.");
			}
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection problem");
		return null;
	}

	public static void closeConnection() throws Exception {
		dbConnection.close();
	}

	public static void closePreparedStatement(PreparedStatement pStatement) throws Exception {
		pStatement.close();
	}

	/**
	 * Test the connection of database. it will get all data from table users.
	 */
	public static void main(String[] args) {
		String testUserSql = "SELECT * FROM SUBJECTS";
		try {
			PreparedStatement testStatement = DBConnection.prepare(testUserSql);
			ResultSet res = testStatement.executeQuery();
			while (res.next()) {
				String code = res.getString(1);
				String name = res.getString(2);
				String coordinator = res.getString(3);
				System.out.printf("code: %s, name: %s, coordinator: %s\n", code, name, coordinator);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}