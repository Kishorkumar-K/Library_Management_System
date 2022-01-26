/**
 * 
 */
package com.project.lms;

import java.util.*;
import java.sql.PreparedStatement;
/**
 * @author kisho
 *
 */
public class Payment {
	Scanner in = new Scanner(System.in);
	conn con = new conn();
	
	public void start() {					//for default payment options if abruptly when a member wants to pay due amt..i mean checks for due amt
		System.out.print("\t\t\t\tEnter the customer ID :");
		String membrid = in.nextLine();
		int due_amt,success;
		
		try {
			String sql = "Select * from member where member_id = ?";
			PreparedStatement st = con.c.prepareStatement(sql);
			st.setString(1, membrid);
			java.sql.ResultSet rs = st.executeQuery();
			if(rs.next()) {
				if(rs.getInt("due_amt")==0) {
					System.out.println("\t\t\t\tNo Due Amount Found!");
				}
				else if(rs.getInt("due_amt")>0){
					System.out.println("\t\t\t\tDue Amount = "+rs.getInt("due_amt"));
					due_amt = rs.getInt("due_amt"); 
					success = make_payment(membrid,due_amt);
					if(success==1) {
						System.out.println("Payment Success");
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public int make_payment(String membrid, int dueamt) {				//this method is called only through addmember.class file for making payment for subscription
		int paid,balanceamt,choice;
		String sql2,sql3,sql4,sql5;
		try {
			/*
			 * below code is used to update in payment table
			 */
			sql2 = "insert into payment(member_id, amount, date, time, status) values(?,?,?,?,?)";
			PreparedStatement st2 = con.c.prepareStatement(sql2);										//used Statement instead of Prepared statement
			st2.setString(1, membrid);
			System.out.println("\t\t\t\tEnter the amount to pay : ");
			paid = in.nextInt();
			if(paid < dueamt) {											// if member paid less than due amt
				balanceamt = dueamt - paid;
				System.out.println("Due Amount : "+balanceamt);
				st2.setInt(2, paid);
			}
			else {														//member paid more than due amount
				balanceamt = paid - dueamt;
				System.out.println("Extra Amount : "+balanceamt);
				System.out.println("Do you wish to contribute some fund for the library");
				System.out.println("1) Yes! with Pleasure");
				System.out.println("2) No not this time");
				choice = in.nextInt();
				if(choice==2) {											// if member denies to pay for funds
					st2.setInt(2, dueamt);
					System.out.println("Here is your balance amount : "+balanceamt);
				}
				if(choice ==1) {											// if the member agrees to pay for funds
					st2.setInt(2, (balanceamt+dueamt));										// stores transaction amount with xtra tip amount
				}
			}
			
			sql3 = "select current_date::date";
			PreparedStatement st3 = con.c.prepareStatement(sql3);
			java.sql.ResultSet rs3 = st3.executeQuery();
			rs3.next();
			st2.setDate(3,rs3.getDate("current_date"));				//stores current date in db
			
			sql4 = "select current_time::time(2)";
			PreparedStatement st4 = con.c.prepareStatement(sql4);
			java.sql.ResultSet rs4 = st4.executeQuery();
			rs4.next();
			st2.setTime(4,rs4.getTime("current_time"));				//stores time in db
			
			st2.setString(5, "success");								//stores status as success
			st2.executeUpdate();
			
			
			
			/*
			 * below code is used to update in member table
			 */
			sql5 = "update member set due_amt = ? where member_id = ?";			//query to update due amt in member table
			PreparedStatement st5 = con.c.prepareStatement(sql5);
			if((paid-dueamt)>=0) {
				st5.setInt(1, 0);								//updates due amt as 0 bcoz member paid xtra amount
			}
			else {
				st5.setInt(1, (dueamt-paid));					//updates due amount as some number bcoz member paid less then dueamt
			}
			st5.setString(2, membrid);
			st5.executeUpdate();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
}
