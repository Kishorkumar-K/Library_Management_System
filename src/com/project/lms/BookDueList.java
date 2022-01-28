/**
 * to display defaulters 
 */
package com.project.lms;


import java.sql.PreparedStatement;

public class BookDueList {

	
	
	public void start() {
		System.out.println("\t\t\t\tBook Due list\n");
		String sql,sql1;
		System.out.println("Book ID\t   Member ID\tDate Issue\tTime Issue\tDate Due");
		System.out.println("-------\t   ---------\t----------\t----------\t--------\n");
		try {
			conn con = new conn();
			sql = "select * from bookledger where date_due < ? and status = 'pending'";			//query to select all records whose due date is less than current date and status is pending
			PreparedStatement st = con.c.prepareStatement(sql);
			
			/*
			 * sql query to get current date
			 */
			sql1 = "select current_date::date";
			PreparedStatement st1 = con.c.prepareStatement(sql1);
			java.sql.ResultSet rs1 = st1.executeQuery();
			rs1.next();
			
			st.setDate(1,rs1.getDate("current_date"));				//passes current date to sql variable
			
			java.sql.ResultSet rs = st.executeQuery();
			
			if(rs.next()) {												//used to print all rows in the table
				do {
					System.out.print("   "+rs.getString("book_id")+"\t");
					System.out.print("       "+rs.getString("member_id")+"\t");
					System.out.print(""+rs.getDate("date_issue")+"\t");
					System.out.print(""+rs.getTime("time_issue")+"\t");
					System.out.print(""+rs.getDate("date_due"));
					System.out.println();
				}while(rs.next());
				
			}
			else {
				System.out.println("Details not Obtained");
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		BookDueList bk = new BookDueList();
		bk.start();
	}
}
