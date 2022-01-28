/**
 * 
 */
package com.project.lms;

import java.util.Scanner;

public class Member {

	private String memberName;
	private String memberId;
	private String mobileNum;
	private String proofType;
	private String proofId;
	private int dueAmt;
	private String address;
	
	
	
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}




	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}




	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}




	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}








	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}




	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}




	/**
	 * @return the mobileNum
	 */
	public String getMobileNum() {
		return mobileNum;
	}




	/**
	 * @param mobileNum the mobileNum to set
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}




	/**
	 * @return the proofType
	 */
	public String getProofType() {
		return proofType;
	}




	/**
	 * @param proofType the proofType to set
	 */
	public void setProofType(String proofType) {
		this.proofType = proofType;
	}




	/**
	 * @return the proofId
	 */
	public String getProofId() {
		return proofId;
	}




	/**
	 * @param proofId the proofId to set
	 */
	public void setProofId(String proofId) {
		this.proofId = proofId;
	}




	/**
	 * @return the dueAmt
	 */
	public int getDueAmt() {
		return dueAmt;
	}




	/**
	 * @param dueAmt the dueAmt to set
	 */
	public void setDueAmt(int dueAmt) {
		this.dueAmt = dueAmt;
	}




	public void start() {
		
		Scanner in = new Scanner(System.in);
		System.out.println("\n\n\n\t\t\t\t1) Member Registration\n");
		System.out.println("\t\t\t\t2) Member Information\n");
		System.out.println("\t\t\t\t3) Member Remove\n");
		System.out.println("\t\t\t\t4) Go Back\n");
		System.out.print("\n\t\tEnter your choice : ");
		int input = in.nextInt();
		if(input == 1)
		{
			MemberRegistration memberRegistration = new MemberRegistration();
			String result = memberRegistration.addMember();
			System.out.println(result);
			
		}
		else if(input == 2)
		{
			System.out.print("\n\n\t\tEnter Member ID :");
			String memberId = in.next();
			MemberInfo memberInfo = new MemberInfo();
			Member member = memberInfo.viewMember(memberId);
			if(member.getAddress() == null)
			{
				System.out.println("\n\t\t\t\t!!!!! Member Details not Found !!!!!\n");
			}
			else
			{
				System.out.println("\n\t\t\t\tMember ID :" + member.getMemberId());
				System.out.println("\t\t\t\tMember Name : " + member.getMemberName());
				System.out.println("\t\t\t\tMember Address : " + member.getAddress());
				System.out.println("\t\t\t\tMember Contact : "+ member.getMobileNum());
				System.out.println("\t\t\t\tMember Proof type : "+ member.getProofType());
				System.out.println("\t\t\t\tMember Proof ID : "+ member.getProofId());
				System.out.println("\t\t\t\tMember Due Amount : " + member.getDueAmt());
				
			}
		}
		else if(input == 3)
		{
			System.out.println("\n\n\n\n\t\t\t\tDelete Member");
			System.out.println("\t\t\t\t-------------\n");
			System.out.print("\t\tEnter Member Id : ");
			String memberId = in.next();
			MemberDelete memberDelete = new MemberDelete();
			String result = memberDelete.deleteMember(memberId);
			System.out.println(result);
		}
		else if(input == 4) {
			//do nothing just goes back to dashboard
		}
		else
		{
			System.out.println("\t\t\t\tInvalid Value");
		}
		
	}
}
