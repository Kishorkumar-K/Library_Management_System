/**
 * 
 */
package com.project.lms;


/**
 * @author kisho
 *
 */
public class LibraryManagementSystem {

	/**
	 * @param args
	 */

	public static void start() {
		
		int ack =0;
		int dsb =0;		
		
		Login login = new Login();										//creating object for login class
		//login.start();
		
		
		System.out.println("                    Welcome to Library Management System\n\n\n");
		
		while(ack != 1) {												//loops until logged in
			if(ack==4||ack==2) {
				System.exit(0);											//if login attempts exhausted or permission not allowed then the program ends
			}
			else{
				ack = login.start();
			}
		}
		
		Dashboard dash = new Dashboard();
		dsb=dash.start();
		
		if(dsb==1) {		//now it logouts user and displays login screen
			LibraryManagementSystem lms = new LibraryManagementSystem();
			lms.start();
					}
		
	}
	 
	 
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		start();
		
		} 
		 
		
		

	

}
