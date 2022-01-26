/**
 * 
 */
package com.project.lms;

import java.sql.*;

/**
 * @author kisho
 *
 */
public class conn {

	Connection c;
	Statement s;
	public conn() {
		try {
			Class.forName("org.postgresql.Driver");
			c =DriverManager.getConnection("jdbc:postgresql://localhost/LibraryManagement","postgres","pass");
			s =c.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
