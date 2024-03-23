package jdbc_test;
import java.sql.*;

public class SchemaInfo {

	public static void main(String[] args) throws SQLException {
		
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		Connection myConn = null;
		ResultSet myRs = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			DatabaseMetaData dbmd = myConn.getMetaData();
			
			// Get list of tables
			System.out.println("List of Tables");
			System.out.println("---------------");
			
			myRs = dbmd.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(myRs.next()){
				System.out.println(myRs.getString("TABLE_NAME"));
			}
			
			// Get list of columns
			System.out.println("\nList of Columns");
			System.out.println("--------------");
			
			myRs = dbmd.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			
			while(myRs.next()){
				System.out.println(myRs.getString("COLUMN_NAME"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			myConn.close();
		}
	}

}
