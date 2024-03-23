package jdbc_test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class ReadBlobDemo {

	public static void main(String[] args) throws IOException, SQLException {
		
		Connection myConn = null;
		Statement myState = null;
		ResultSet myRs = null;
		
		InputStream input = null;
		FileOutputStream output = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			
			myState = myConn.createStatement();
			String sql = "select resume from employees where email = 'john.doe@foo.com'";
			myRs = myState.executeQuery(sql);
			
			File myFile = new File("D:\\JAVA\\jdbc_test\\src\\resume_from_db.pdf");
			output = new FileOutputStream(myFile);
			
			if(myRs.next()){
				input = myRs.getBinaryStream("resume");
				System.out.println("Reading resume from database...");
				System.out.println(sql);
				
				byte[] buffer = new byte[1024];
				while(input.read(buffer) > 0){
					output.write(buffer);
				}
				System.out.println("Completed!!");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input != null){
				input.close();
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
