package jdbc_test;

import java.sql.*;

public class JdbcDelete {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myState = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";

		try {
			myConn = DriverManager.getConnection(dbUrl, user, pass);
			myState = myConn.createStatement();

			System.out.println("BEFORE DELETING THE INFO OF EMPLOYEE...");
			displayEmployee(myState, "Eric", "Wright");

			System.out.println("\nDELETING THE EMPLOYEE'S INFO...");
			int rowsAffected = myState.executeUpdate("delete from employees "
					+ "where last_name = 'Wright' and first_name = 'Eric'");

			System.out.println("\nAFTER DELETING THE EMPLOYEE'S INFO");
			displayEmployee(myState, "Eric", "Wright");

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
		boolean flag = true;
		ResultSet myRs = null;
		
		try {
			myRs = myState.executeQuery("select * from employees");
			while (myRs.next()) {
				if ((first_name.equals(myRs.getString("first_name")))
						&& last_name.equals(myRs.getString("last_name"))) {
					System.out.println(myRs.getString("first_name") + " " + myRs.getString("last_name"));
					flag = false;
				}
			}
			if (flag == true) {
				System.out.println("EMPLOYEE NOT FOUND: " + first_name + " " + last_name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			myRs.close();
		}

	}

}
