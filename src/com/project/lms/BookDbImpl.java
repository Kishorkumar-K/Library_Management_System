/**
 * for database operations on book
 */
package com.project.lms;

import java.sql.PreparedStatement;

/**
 * @author kisho
 *
 */
public class BookDbImpl implements BookDbo{

	conn con = new conn();
	
	
	public int checkDueDate(String memberid, String bookid) {			//this method returns the count of days xtra after due date
		
		try {
			
			String sql="select date_due - (select current_date::date) as difference from bookledger where (status = 'pending') and (member_id = ?) and (book_id = ?) limit 1; ";
			PreparedStatement st = con.c.prepareStatement(sql);
			st.setString(1, memberid);
			st.setString(2, bookid);
			java.sql.ResultSet rs = st.executeQuery();
			if(!rs.wasNull()) {
				rs.next();
				return rs.getInt("difference");
			}
			else {
				System.out.println("No values returned inside check due date method");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return 0;
	}
	
	
	
	public int fineAmount(int day) {
		
		//fine per day per book is Rs. 5
		return (day*5);
	}
	
	
	
	public String updateStatus(String memberid, String bookid) {
		String result="";
		conn con = new conn();
		
		try {
			
			String sql1 = "UPDATE bookledger SET status = 'returned', date_return = ? WHERE (member_id = ?) and (book_id = ?)";			//query to update status, date_return in bookledger table
			PreparedStatement st1 = con.c.prepareStatement(sql1);
			/*
			 * query to get current date
			 */
			
			String sql4 = "select current_date::date";
			PreparedStatement st4 = con.c.prepareStatement(sql4);
			java.sql.ResultSet rs4 = st4.executeQuery();
			rs4.next();
			
			st1.setDate(1,rs4.getDate("current_date"));				//stores current date in db
			
			st1.setString(2, memberid);
			st1.setString(3, bookid);
			st1.executeUpdate();
			result = "Book Status Updated Successfully";
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			result = "Some problem with update due amount method";
		}
		return result;
	}
	
	
	
	
	
	public String updateBookAvailableCount(String bookid) {					//this method used to update available_count in book table
		String result="";
		
try {
	
	/*
	 * first get the availabe_count values from table and then increment it by one and then update its count
	 */
	
	String sql2 = "select * from book where book_id = ?";			//used to get the available count of that particular book
	PreparedStatement st2 = con.c.prepareStatement(sql2);
	st2.setString(1, bookid);
	java.sql.ResultSet rs2 = st2.executeQuery();
	
	if(rs2.next()) {
		
		if((rs2.getInt("available_count")+1) <= rs2.getInt("total_count")) {							
			int ava_count = rs2.getInt("available_count");
			ava_count+=1;				
	
			String sql3 = "UPDATE book SET available_count = ? WHERE book_id = ?";			//query to update available_count in book table
			PreparedStatement st3 = con.c.prepareStatement(sql3);
			st3.setInt(1, ava_count);
			st3.setString(2, bookid);
			st3.executeUpdate();
			result = "Available Count in book table is Updated Successfully";
			return result;
		}
		else {
			System.out.println("available count is greater than total count..logical error check the code");
		}
	}
	else {
		System.out.println("Available book count is not retuning from table..check the code");
		}
	}
	catch(Exception e) {
		e.printStackTrace();
		result = "Some problem with update due amount method";
	}
		
		
		
		
		
		return result;
	}
}
