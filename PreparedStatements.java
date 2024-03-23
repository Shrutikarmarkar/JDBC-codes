package jdbc_test;
import java.sql.*;

public class PreparedStatements {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		ResultSet myRs = null;
		
		String dbUrl = "jdbc:mysql://localhost:3306/demo";
		String user = "student";
		String pass = "student";
		
		try{
			myConn = DriverManager.getConnection(dbUrl, user, pass);
			PreparedStatement myState =  myConn.prepareStatement("select * from employees " +
																	"where salary > ? and department = ?");
			
			myState.setDouble(1, 80000);
			myState.setString(2, "Legal");
			
			myRs = myState.executeQuery();
			
			while(myRs.next()){
				System.out.println(myRs.getString("first_name") + " " +
						myRs.getString("last_name") + " " +
						myRs.getString("salary") + " " + 
						myRs.getString("department"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (myRs != null){
				myRs.close();
			}
			if (myConn != null){
				myConn.close();
			}
		}
		
	}

}
