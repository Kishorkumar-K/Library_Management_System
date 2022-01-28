/**
 * 
 */
package com.project.lms;

import java.sql.PreparedStatement;
import java.util.Scanner;


public class BookInfo {
	Scanner in = new Scanner(System.in);
	
	
	public void start() {
		
		Book book = new Book();
		
		System.out.println("\n\n\n\n\t\t\t\t\t\tBook Info");
		System.out.println("\t\t\t\t\t\t---------\n\n");
		System.out.println("\t\t\t\tSearch the Book by:\n");
		System.out.println("\t\t\t\t1) Book Title\n\t\t\t\t2) Book Author\n\t\t\t\t3) Book ID\n\t\t\t\t4) Book Title & Book Author\n\t\t\t\t5) Go Back to Dashboard");
		System.out.print("\n\n\t\tEnter Your Choice : ");
		int choice = in.nextInt();
		switch(choice) {
		case 1:
			System.out.print("\n\t\t\t\tEnter the Book Title :");
			book.setTitle(in.next());
			search(book.getTitle(),1);
			break;
		
		case 2:
			System.out.print("\n\t\t\t\tEnter Author Name : ");
			book.setAuthor(in.next());
			search(book.getAuthor(),2);
			break;
			
		case 3:
			System.out.print("\n\t\t\t\tEnter Book ID : ");
			book.setBookid(in.next());
			search(book.getBookid(),3);
			break;
			
		case 4:
			System.out.print("\n\t\t\t\tEnter Author Name : ");
			book.setAuthor(in.next());
			System.out.print("\n\t\t\t\tEnter Book Title : ");
			book.setTitle(in.next());
			search(book.getTitle(),book.getAuthor());
			break;
			
		case 5:
			//do nothing...so it goes back to dashboard
		}
	}
	public void search(String search,int ch) {	//method overloading...applicable for switch option 1,2,3
		
		String sql="";
		search+='%';							//appends % symbol at the end to search the text starting with the value of search variable
		try {
			conn con = new conn();
			if(ch==1) {															//query to get info about book based on book title
				sql = "select * from book where book_title like ?";
			}
			else if(ch==2) {																//query to get info about book based on author
				sql = "select * from book where book_author like ?";
			}
			else if(ch==3) {																//query to get info about book based on bookid
				sql = "select * from book where book_id like ?";
			}
			PreparedStatement st = con.c.prepareStatement(sql);
			st.setString(1, search);
			
			java.sql.ResultSet rs = st.executeQuery();
			if(rs.next()) {
				System.out.println("\t\t\t\tBook ID : "+rs.getString("book_id"));
				System.out.println("\t\t\t\tBook Title : "+rs.getString("book_title"));
				System.out.println("\t\t\t\tBook Author : "+rs.getString("book_author"));
				System.out.println("\t\t\t\tBook Publication : "+rs.getString("book_publication"));
				System.out.println("\t\t\t\tBook Row : "+rs.getString("book_row"));
				System.out.println("\t\t\t\tBook Total Count : "+rs.getString("total_count"));
				System.out.println("\t\t\t\tBook Available Count : "+rs.getString("available_count"));
			}
			else {
				System.out.println("\t\t\t\tNo Such Thing Found!\n\t\tTry using different keywords or check for spellings");
				start();										//goes back to first of bookinfo page
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void search(String title, String author) {	//method overloading..to get book info based on book author and book title
		
		title+='%';
		author+='%';
		try {
			conn con = new conn();
			String sql = "select * from book where book_title like ? and book_author like ?";
			PreparedStatement st1 = con.c.prepareStatement(sql);
			st1.setString(1, title);
			st1.setString(2, author);
			java.sql.ResultSet rs1 = st1.executeQuery();
			if(rs1.next()) {
				System.out.println("\t\t\t\tBook ID : "+rs1.getString("book_id"));
				System.out.println("\t\t\t\tBook Title : "+rs1.getString("book_title"));
				System.out.println("\t\t\t\tBook Author : "+rs1.getString("book_author"));
				System.out.println("\t\t\t\tBook Publication : "+rs1.getString("book_publication"));
				System.out.println("\t\t\t\tBook Row : "+rs1.getString("book_row"));
				System.out.println("\t\t\t\tBook Total Count : "+rs1.getString("total_count"));
				System.out.println("\t\t\t\tBook Available Count : "+rs1.getString("available_count"));
			}
			else {
				System.out.println("\t\t\t\tNo Such Thing Found!\n\tTry using different keywords or check for spellings");
				start();										//goes back to first of bookinfo page
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
