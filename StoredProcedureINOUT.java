package jdbc_test;

import java.sql.*;

public class StoredProcedureINOUT {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement state = null;

		try {
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");

			String department = "Engineering";

			System.out.println("CALLING THE STORED PROCEDURE greet_the_department('" + department + "')");
			state = myConn
					.prepareCall("{call greet_the_department(?)}");

			state.registerOutParameter(1, Types.VARCHAR);
			state.setString(1, department);
			
			System.out.println("EXECUTING THE STORED PROCEDURE...");
			state.execute();
			
			String results = state.getString(1);
			System.out.println("Results:  " + results);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (state != null) {
				state.close();
			}
			if (myConn != null) {
				myConn.close();
			}

		}
	}
}
