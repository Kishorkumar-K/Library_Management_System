/**
 * 
 */
package com.project.lms;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class MemberRegistration {

	MemberDbo memberdbo = new MemberDbImpl();
	
	public String addMember(){
		String result="";
		try {
			
			Scanner in = new Scanner(System.in);
			Member member = new Member();
			
			System.out.println("\t\tPlease Enter the Following Details");
			
			System.out.println("\t\t\t\tMember Name : ");
			member.setMemberName(in.nextLine());
			
			System.out.println("\t\t\t\tMember Address : ");
			member.setAddress(in.nextLine());
			
			System.out.println("\t\t\t\tMember Contact : ");
			member.setMobileNum(in.nextLine());
			
			System.out.println("\t\t\t\tMember Proof Type : ");
			member.setProofType(in.nextLine());
			
			System.out.println("\t\t\t\tMember Proof ID : ");
			member.setProofId(in.nextLine());
			
			
			result = memberdbo.addMember(member);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
