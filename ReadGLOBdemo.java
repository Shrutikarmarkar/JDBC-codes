package jdbc_test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class ReadGLOBdemo {

	public static void main(String[] args) throws IOException, SQLException {
		Connection myConn = null;
		Statement myState = null;
		ResultSet myRs = null;
		
		Reader input = null;
		FileWriter output = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			
			myState = myConn.createStatement();
			String sql = "select resume from employees where email = 'john.doe@foo.com'";
			myRs = myState.executeQuery(sql);
			
			File myFile = new File("D:\\JAVA\\jdbc_test\\src\\jdbc_test\\resume_from_db.txt");
			output = new FileWriter(myFile);
			
			if(myRs.next()){
				input = myRs.getCharacterStream("resume");
				System.out.println("Reading Resume from database...");
				System.out.println(sql);
				
				int theChar;
				while((theChar = input.read())> 0){
					output.write(theChar);
				}
			}
			
			System.out.println("\nCompleted!!");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(input != null){
				output.close();
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
