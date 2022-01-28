/**
 * 
 */
package com.project.lms;

import java.util.Scanner;


public class Dashboard {

	public int start() {
		Scanner in = new Scanner(System.in);
		int choice;
		
		System.out.println("\n\n\t\t\t\t\tMIT Library Management System");
		System.out.println("\t\t\t\t\t-----------------------------\n\n");
		System.out.println("\t\t\t\t1) Member\n");
		System.out.println("\t\t\t\t2) Issue Books\n");
		System.out.println("\t\t\t\t3) Return Books\n");
		System.out.println("\t\t\t\t4) Book Info\n");
		System.out.println("\t\t\t\t5) Due List\n");
		System.out.println("\t\t\t\t6) Payment\n");
		System.out.println("\t\t\t\t7) Logout\n\n");
		
		System.out.print("\t\tEnter your Choice : ");
		choice=in.nextInt();												//gets input choice from user
		
		switch(choice) {
		
		case 1:
			Member member  = new Member();
			member.start();
			break;
			
		case 2:
			BookIssue bkissue = new BookIssue();
			bkissue.start();
			break;
		
		case 3:
			BookReturn bkret = new BookReturn();
			bkret.start();
			break;
			
		case 4:
			BookInfo bkinfo = new BookInfo();
			bkinfo.start();
			break;
		
		case 5:
			BookDueList duelst = new BookDueList();
			duelst.start();
			break;
		
		case 6:
			Payment pmt = new Payment();
			pmt.start();
			break;
			
		case 7:
			//Logout
			return 1;								//returns 1 and goes back to LibraryManagementSystem.class file
		}
		start();											//calling method inside the method inorder to resume from member
		return 0;									//default return type ...this means some error occurred
	}
}
