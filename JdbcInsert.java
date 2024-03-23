package jdbc_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcInsert {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myState = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";

		try {
			// 1. Get a connection to the database
			myConn = DriverManager.getConnection(dbUrl, user, pass);
			System.out.println("Database connection successfull!\n");

			// 2. Create a statement
			myState = myConn.createStatement();

			// 3. Insert A new Employee
			System.out.println("Inserting a new employee to database\n");

			int rowsAffected = myState
					.executeUpdate("insert into employees "
							+ "(last_name, first_name, email, department, salary) "
							+ "values "
							+ "('Wright', 'Eric', 'eric.wrigh@foo.com', 'HR', 33000.00)");

			myRs = myState.executeQuery("select * from employees order by last_name");

			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", "
						+ myRs.getString("first_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (myRs != null) {
				myRs.close();
			}

			if (myState != null) {
				myState.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

	}

}
