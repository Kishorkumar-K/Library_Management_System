/**
 * 
 */
package com.project.lms;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;
/**
 * @author kisho
 *
 */
public class MemberRegistration {

	MemberDbo memberdbo = new MemberDbImpl();
	
	public String addMember(){
		String result="";
		try {
			
			Scanner in = new Scanner(System.in);
			Member member = new Member();
			
			System.out.println("Please Enter the Following Details");
			
			System.out.println("Member Name : ");
			member.setMemberName(in.nextLine());
			
			System.out.println("Member Address : ");
			member.setAddress(in.nextLine());
			
			System.out.println("Member Contact : ");
			member.setMobileNum(in.nextLine());
			
			System.out.println("Member Proof Type : ");
			member.setProofType(in.nextLine());
			
			System.out.println("Member Proof ID : ");
			member.setProofId(in.nextLine());
			
			/*String memberId = UUID.randomUUID().toString();
			Long l = Long.parseLong(memberId);
			member.setMemberId(l);
			*/
			result = memberdbo.addMember(member);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
