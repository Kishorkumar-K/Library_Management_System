/**
 * 
 */
package com.project.lms;

import java.sql.PreparedStatement;
import java.util.*;

public class Login {
	static int login_attempts=0;
	
	public int start (){			
		
		String username,password;
		int total_attempts = 3;
		Scanner in = new Scanner(System.in);
		
		
		System.out.print("\t\t\tEnter Username : ");
		username=in.nextLine();										//gets username input from user
		System.out.print("\t\t\tEnter Password : ");
		password = in.nextLine();									//gets password input from user
		
		login_attempts++;											//increments counter of login attempt
		if(login_attempts<3) {
			try {
				conn con = new conn();
				String sql = "select * from login where uname=? and password=?";			//query to check username and password
				PreparedStatement st = con.c.prepareStatement(sql);
				st.setString(1, username);
				st.setString(2, password);
				java.sql.ResultSet rs = st.executeQuery();
				if(rs.next()) {
					if(rs.getInt("allowed")==1) {
						System.out.println("\n\t\t\t\t\t***** Login Successful *****\n\n\n\n");	//if login credentials are correct and user is allowed to login
						con.c.close();
						return 1;
					}
					else {
						System.out.println("\n\t\t\t\t!!!!! Login Successful but used not allowed !!!!!");		//if login credentials are correct and user is not allowed to login
						con.c.close();
						return 2;
					}
				}
				else {
					System.out.println("\n\n\t\t\t!!!!! Wrong login credentials !!!!!");
					System.out.println("\n\t\t\t( Remaining Login Attempts : "+(total_attempts-login_attempts)+")\n\n\n\n\n\n\n\n\n");
					con.c.close();
					return 3;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("\n\t\t\t##### Login Attempts Exhausted! Try Again!!! #####");
			return 4;
		}
		
		return 5;
	}
	
	
	/*
	 * 1 - login successful and permission allowed
	 * 2 - login successful and permission not allowed
	 * 3 - wrong credentials
	 * 4 - login attempts exhausted
	 * 5 - unexpected error try block doesn't work
	 */
}
