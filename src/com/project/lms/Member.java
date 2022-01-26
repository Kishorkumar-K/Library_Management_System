/**
 * 
 */
package com.project.lms;

import java.util.Scanner;
/**
 * @author kisho
 *
 */
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
		System.out.println("1. Member Registration");
		System.out.println("2. Member Information");
		System.out.println("3. Member Remove");
		System.out.println("Enter Valid Value:");
		int input = in.nextInt();
		if(input == 1)
		{
			MemberRegistration memberRegistration = new MemberRegistration();
			String result = memberRegistration.addMember();
			System.out.println(result);
			
		}
		else if(input == 2)
		{
			System.out.println("Enter Member Id :");
			String memberId = in.next();
			MemberInfo memberInfo = new MemberInfo();
			Member member = memberInfo.viewMember(memberId);
			if(member.getAddress() == null)
			{
				System.out.println("Member Details not Found");
			}
			else
			{
				System.out.println("Member Id :" + member.getMemberId());
				System.out.println("Member Name : " + member.getMemberName());
				System.out.println("Member Address : " + member.getAddress());
				System.out.println("Member Contact : "+ member.getMobileNum());
				System.out.println("Member Proof type : "+ member.getProofType());
				System.out.println("Member Proof ID : "+ member.getProofId());
				System.out.println("Member Due Amount : " + member.getDueAmt());
				
			}
		}
		else if(input == 3)
		{
			System.out.println("Enter Member Id :");
			String memberId = in.next();
			MemberDelete memberDelete = new MemberDelete();
			String result = memberDelete.deleteMember(memberId);
			System.out.println(result);
		}
		else
		{
			System.out.println("Invalid Value");
		}
	}
}
