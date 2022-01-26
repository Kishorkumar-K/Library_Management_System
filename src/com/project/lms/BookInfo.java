/**
 * 
 */
package com.project.lms;

import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author kisho
 *
 */
public class BookInfo {
	Scanner in = new Scanner(System.in);
	conn con = new conn();
	
	public void start() {
		
		Book book = new Book();
		
		System.out.println("Book Info");
		System.out.println("How do u want to search the book");
		System.out.println("1) Book Title\n2) Book Author\n3) Book ID\n4)Book Title & Book Author\n5) Go Back to Dashboard");
		System.out.print("\n\nEnter Your Choice : ");
		int choice = in.nextInt();
		switch(choice) {
		case 1:
			System.out.print("Enter the Book Title :");
			book.setTitle(in.next());
			search(book.getTitle(),1);
			break;
		
		case 2:
			System.out.println("Enter Author Name : ");
			book.setAuthor(in.next());
			search(book.getAuthor(),2);
			break;
			
		case 3:
			System.out.println("Enter Book ID : ");
			book.setBookid(in.next());
			search(book.getBookid(),3);
			break;
			
		case 4:
			System.out.println("Enter Author Name : ");
			book.setAuthor(in.next());
			System.out.println("Enter Book Title : ");
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
				System.out.println("Book ID : "+rs.getString("book_id"));
				System.out.println("Book Title : "+rs.getString("book_title"));
				System.out.println("Book Author : "+rs.getString("book_author"));
				System.out.println("Book Publication : "+rs.getString("book_publication"));
				System.out.println("Book Row : "+rs.getString("book_row"));
				System.out.println("Book Total Count : "+rs.getString("total_count"));
				System.out.println("Book Available Count : "+rs.getString("available_count"));
			}
			else {
				System.out.println("No Such Thing Found!\n Try using different keywords or check for spellings");
				start();										//goes back to first of bookinfo page
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void search(String title, String author) {	//method overloading..to get book info based on book author and book title
		
		title+='%';
		author+='%';
		try {
			String sql = "select * from book where book_title like ? and book_author like ?";
			PreparedStatement st1 = con.c.prepareStatement(sql);
			st1.setString(1, title);
			st1.setString(2, author);
			java.sql.ResultSet rs1 = st1.executeQuery();
			if(rs1.next()) {
				System.out.println("Book ID : "+rs1.getString("book_id"));
				System.out.println("Book Title : "+rs1.getString("book_title"));
				System.out.println("Book Author : "+rs1.getString("book_author"));
				System.out.println("Book Publication : "+rs1.getString("book_publication"));
				System.out.println("Book Row : "+rs1.getString("book_row"));
				System.out.println("Book Total Count : "+rs1.getString("total_count"));
				System.out.println("Book Available Count : "+rs1.getString("available_count"));
			}
			else {
				System.out.println("No Such Thing Found!\n Try using different keywords or check for spellings");
				start();										//goes back to first of bookinfo page
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
