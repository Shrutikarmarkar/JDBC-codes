package jdbc_test;
import java.sql.*;

public class StoredProcedureResultSet {

	public static void main(String[] args) throws SQLException {
			Connection myConn = null;
			CallableStatement state = null;
			ResultSet results = null;

			try {
				myConn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/demo", "student", "student");

				String department = "Engineering";

				System.out.println("CALLING THE STORED PROCEDURE get_employees_for_department('" + department + "')");
				state = myConn
						.prepareCall("{call get_employees_for_department(?)}");

				state.setString(1, department);
				
				System.out.println("EXECUTING THE STORED PROCEDURE...");
				state.execute();
				
				results = state.getResultSet();
				displayresults(myConn, results);


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

	private static void displayresults(Connection myConn, ResultSet results) throws SQLException {
		Statement myState = myConn.createStatement();
		ResultSet myResult = results;
		try{
			while(myResult.next()){
				System.out.println(myResult.getString("first_name") + " " + myResult.getString("last_name")+
						" " + myResult.getString("department") + " " + myResult.getString("salary"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (myResult != null){
				myResult.close();
			}
		}
		
	}

}
