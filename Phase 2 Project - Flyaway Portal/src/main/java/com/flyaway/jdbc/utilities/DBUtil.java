package com.flyaway.jdbc.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
	
	public static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			System.out.println("Inside DbUtil.close(myConn, myStmt, myRs)...");
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close(); //returns it to the connection pool for use
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//end close()

}//end class
