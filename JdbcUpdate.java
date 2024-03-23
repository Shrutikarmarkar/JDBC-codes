package jdbc_test;

import java.sql.*;

public class JdbcUpdate {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myState = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";

		try {
			// 1. Get a connection to the database
			myConn = DriverManager.getConnection(dbUrl, user, pass);
			// 2. Create a statement
			myState = myConn.createStatement();

			// Call helper method to display the employee's information
			System.out.println("BEFORE THE UPDATE...");
			displayEmployee(myState, "John", "Doe");

			System.out.println("\nEXECUTING THE UPDATE FOR: John Doe\n");
			// 3. Update the Email of an employee
			int rowsAffected = myState.executeUpdate("update employees "
					+ "set email = 'john.doe@luv2code.com' "
					+ "where last_name = 'Doe' and first_name = 'John'");

			// 4. Process the result set
			System.out.println("AFTER THE UPDATE...");
			displayEmployee(myState, "John", "Doe");


		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (myState != null) {
				myState.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

	private static void displayEmployee(Statement myState, String first_name,
			String last_name) throws SQLException {
		ResultSet myRs = null;
		try {
			myRs = myState.executeQuery("select * from employees");
			while (myRs.next()) {
				if ((last_name.equals(myRs.getString("last_name")))
						&& (first_name.equals(myRs.getString("first_name")))) {
					System.out.println(myRs.getString("email"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (myRs != null) {
				myRs.close();
			}
		}

	}

}
