package jdbc_test;
import java.sql.*;

public class ResultSetDemo {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myState = null;
		ResultSet Rset = null;
		
		try{
			// get connection to the database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			
			// Execute Query
			myState = myConn.createStatement();
			Rset = myState.executeQuery("select id, first_name, last_name, salary from employees");
			
			// Get result set MetaData
			ResultSetMetaData ResultMetaData = Rset.getMetaData();
			
			// Display info
			int columnCount = ResultMetaData.getColumnCount();
			System.out.println("Column Count: "+ columnCount);
			
			for(int column=1; column<=columnCount ; column++){
				System.out.println("Column Name: " + ResultMetaData.getColumnName(column));
				System.out.println("Column Type: " + ResultMetaData.getColumnTypeName(column));
				System.out.println("Is Nullable: " + ResultMetaData.isNullable(column));
				System.out.println("Is Auto Increment: " + ResultMetaData.isAutoIncrement(column) + "\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(Rset != null){
				Rset.close();
			}
			if(myState != null){
				myState.close();
			}
			if(myConn != null){
				myConn.close();
			}
		}
	}

}
