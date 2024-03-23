package jdbc_test;

import java.sql.*;

public class StoredProcedureOUT {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement state = null;

		try {
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demo", "student", "student");

			String department = "Engineering";

			System.out
					.println("CALLING THE STORED PROCEDURE get_count_for_department('"
							+ department + "')");
			state = myConn.prepareCall("{call get_count_for_department(?, ?)}");

			state.setString(1, department);
			state.registerOutParameter(2, Types.INTEGER);

			System.out.println("EXECUTING THE STORED PROCEDURE...");
			state.execute();

			int results = state.getInt(2);
			System.out.println("Total " + department + " departments are: " + results);

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
