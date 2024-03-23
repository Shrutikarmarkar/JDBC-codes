package jdbc_test;

import java.sql.*;

public class StoredProcedureIN {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		CallableStatement state = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			
			String department = "Engineering";
			int increase = 10000;
			
			System.out.println("SALARY BEFORE...");
			get_salary(myConn, department);
			
			System.out.println("\nIncreasing the Salaries...");
			state = myConn.prepareCall("{call increase_salaries_for_department(?, ?)}");
			
			state.setString(1, department);
			state.setInt(2, increase);
			
			state.execute();
			System.out.println("Salary increased by " + increase + " for the department: " + department);

			
			System.out.println("\nSALARY AFTER");
			get_salary(myConn, department);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (state != null){
				state.close();
			}
			if (myConn != null){
				myConn.close();
			}
		}
		

	}

	private static void get_salary(Connection myConn, String department) throws SQLException {
		Statement myState = myConn.createStatement();
		ResultSet myResult = null;
		try{
			myResult = myState.executeQuery("select * from employees where department = 'Engineering'");
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
