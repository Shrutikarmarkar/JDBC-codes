package jdbc_test;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


public class SecureConnectionDemo {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myState = null;
		ResultSet myRs = null;
		
		try{
			Properties prop = new Properties();
			prop.load(new FileInputStream("D:\\JAVA\\jdbc_test\\src\\jdbc_test\\DemoProperties"));
			
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String dburl = prop.getProperty("dburl");
			
			System.out.println("Connecting to the Database...");
			System.out.println("Usename: " + user);
			System.out.println("URL: " + dburl);
			
			myConn = DriverManager.getConnection(dburl, user, password);
			System.out.println("\nConnection Successfull!!");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(myRs != null){
				myRs.close();
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
