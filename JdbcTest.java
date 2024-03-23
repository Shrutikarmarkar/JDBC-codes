package jdbc_test;

import java.sql.*;

public class JdbcTest {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myState = null;
		ResultSet myRs = null;

		try {
			// 1. Get a connection to the database
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");
			System.out.println("Database connection successfull!\n");

			// 2. Create a statement
			myState = myConn.createStatement();

			// 3. Execute SQL query
			myRs = myState.executeQuery("select * from employees");

			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", "
						+ myRs.getString("first_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if (myRs != null){
				myRs.close();
			}
			
			if (myState != null){
				myState.close();
			}
			
			if (myConn != null){
				myConn.close();
			}
		}

	}

}
