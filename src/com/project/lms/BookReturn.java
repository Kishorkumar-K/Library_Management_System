/**
 * 
 */
package com.project.lms;

import java.sql.SQLException;
import java.util.Scanner;

public class BookReturn {

	public void start() {
		String result="";
		Scanner in = new Scanner(System.in);
		Book book = new Book();
		try {
			
			System.out.print("\n\t\tEnter Member ID : ");
			book.setMemberid(in.next());
			
			System.out.print("\n\t\tEnter number of books : ");
			int count = in.nextInt();
			
			while(count>0) {
				System.out.print("\n\t\tEnter Book ID : ");
				book.setBookid(in.next());
				
				MemberDbo memberdbo = new MemberDbImpl();
				BookDbo bookdbo = new BookDbImpl();
				
				int day = bookdbo.checkDueDate(book.getMemberid(),book.getBookid());
				if(day<0) {																//if book is returned after due date...ie., day<0
					day=-day;															// converting negative into positive
					int fineamt = bookdbo.fineAmount(day);
					
												//updates in member table of the column due amount
					result = memberdbo.updateDueAmount(book.getMemberid(), fineamt);    //if success then prints Due Amount Updated Successfully
					System.out.println(result);
					
				}
				
				result = bookdbo.updateStatus(book.getMemberid(),book.getBookid());
				System.out.println(result);
				
				result = bookdbo.updateBookAvailableCount(book.getBookid());
				System.out.println(result);
				
				count--;

			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
