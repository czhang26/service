package com.azure.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SQLDatabaseConnection {

	public Person connection(String email) {
		// String QUERY = "SELECT email, firstName, lastName FROM Person where email =
		// 'liqun9@yahoo.com'";
		String QUERY = "SELECT email, firstName, lastName FROM Person where email = '" + email + "'";
		Person person = new Person();
		// String connectionUrl =
		// "jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;integratedSecurity=true";
		String connectionUrl = "jdbc:sqlserver://localhost;Database=zuzong;user=alanna;password=zhang";
		// String connectionUrl =
		// "jdbc:sqlserver://localhost;Database=zuzong;integratedSecurity=true;authenticationscheme=NTLM;domain=liqun9-PC;user=liqun9;password=Aldstad1";
		try {
			System.out.print("Connecting to SQL Server ... ");
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(driver).newInstance();

			// try (Connection connection = DriverManager.getConnection(connectionUrl)) {

			Connection connection = DriverManager.getConnection(connectionUrl);
			System.out.println("Done.");

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String email2 = rs.getString("email");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				person.setEmail(email2);
				person.setFirstName(firstName);
				person.setLastName(lastName);
				System.out.print(", email: " + rs.getString("email"));
				System.out.print(", First: " + rs.getString("firstName"));
				System.out.println(", Last: " + rs.getString("lastName"));
			}

		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}
		return person;
	}

}
