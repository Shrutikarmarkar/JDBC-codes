package jdbc_test;
import java.sql.*;

public class metaDataBasicInfo {

	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			DatabaseMetaData dbmd = myConn.getMetaData();
			
			System.out.println("Product name: " + dbmd.getDatabaseProductName());
			System.out.println("Product version: " + dbmd.getDatabaseProductVersion());
			System.out.println();
			
			System.out.println("Driver name: " + dbmd.getDriverName());
			System.out.println("Driver version: " + dbmd.getDriverVersion());

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			myConn.close();
		}
		
		

	}

}
