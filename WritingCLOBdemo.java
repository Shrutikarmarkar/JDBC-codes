package jdbc_test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class WritingCLOBdemo {

	public static void main(String[] args) throws IOException, SQLException {
		Connection myConn = null;
		PreparedStatement myState = null;
		
		FileReader input = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");
			
			String sql = "update employees set resume = ? where email = 'john.doe@foo.com'";
			myState = myConn.prepareStatement(sql);
			
			File myFile = new File("D:\\JAVA\\jdbc_test\\src\\jdbc_test\\sample_resume.txt");
			input = new FileReader(myFile);
			myState.setCharacterStream(1, input);
			
			System.out.println("Reading input file " + myFile.getAbsolutePath());
			System.out.println("\nResume stored in database " + myFile);
			System.out.println(sql);
			
			myState.executeUpdate();
			
			System.out.println("\nDatabase Updated!!");
			
			
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
