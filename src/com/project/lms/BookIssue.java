/**
 * 
 */
package com.project.lms;

import java.sql.PreparedStatement;
import java.util.Scanner;


public class BookIssue {

	
	
	public void start() {
		
		 
		int count;
		Scanner in = new Scanner(System.in);
		
		Book book = new Book();						//wrapper class for varaiables
		
		System.out.println("\n\n\t\t\t\tIssue Books");
		System.out.println("\t\t\t\t-----------\n\n");
		System.out.print("\t\tEnter member ID : ");
		book.setMemberid(in.next());
		System.out.print("\n\t\tEnter count of books : ");
		count = in.nextInt();
		
		try {
			conn con = new conn();
			for(int i=0;i<count;i++) {
				System.out.print("\n\t\tEnter Book ID : ");
				book.setBookid(in.next());
				
				String sql = "select * from book where book_id = ?";			//used to get the available count of that particular book
				PreparedStatement st = con.c.prepareStatement(sql);
				st.setString(1, book.getBookid());
				java.sql.ResultSet rs = st.executeQuery();
				
				if(rs.next()) {
					if(rs.getInt("available_count")>0) {							//checking if book stock available
						int ava_count = rs.getInt("available_count");
						ava_count-=1;												//decreasing the available count by 1
						
						String sql1 = "update book set available_count = ? where book_id = ?";			//sql query to update the available count in book table
						PreparedStatement st1 = con.c.prepareStatement(sql1);
						st1.setInt(1, ava_count);
						st1.setString(2, book.getBookid());
						st1.executeUpdate();
						System.out.println("Book count updated in book table\n");
						
											
						
						String sql2 = "INSERT INTO bookledger(book_id, member_id, date_issue, date_due, time_issue, status) VALUES (?, ?, ?, ?, ?, ?)";			//sql query to insert data in bookledger table
						PreparedStatement st2 = con.c.prepareStatement(sql2);
						st2.setString(1, book.getBookid());
						st2.setString(2, book.getMemberid());
						
						/*
						 * to get current date
						 */
						String sql3 = "select current_date::date";
						PreparedStatement st3 = con.c.prepareStatement(sql3);
						java.sql.ResultSet rs3 = st3.executeQuery();
						rs3.next();
						st2.setDate(3,rs3.getDate("current_date"));				//stores current date in db
						
						/*
						 * to get due date (ie.,) after 15 days
						 */
						String sql4 = "select current_date::date + 15 as currentdate";
						PreparedStatement st4 = con.c.prepareStatement(sql4);
						java.sql.ResultSet rs4 = st4.executeQuery();
						rs4.next();
						st2.setDate(4,rs4.getDate("currentdate"));				//stores current date in db
						
						/*
						 * to get current time
						 */
						String sql5 = "select current_time::time(2)";
						PreparedStatement st5 = con.c.prepareStatement(sql5);
						java.sql.ResultSet rs5 = st5.executeQuery();
						rs5.next();
						st2.setTime(5,rs5.getTime("current_time"));				//stores time in db
						
						st2.setString(6,"pending");
						//st2.setDate(7,""); no need to insert date return now bcoz it is only updated after returning the book
						
						st2.executeUpdate();
						System.out.println("\t\t\t\tData Stored in bookledger");
					}
					else {
						System.out.println("Stock not available...check the code man");
					}
				}
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
