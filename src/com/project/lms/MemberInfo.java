/**
 * 
 */
package com.project.lms;

/**
 * @author kisho
 *
 */
public class MemberInfo {

	public Member viewMember(String memberId) {
		
		MemberDbo  memberdbo = new MemberDbImpl();
		Member member = null;
		try {
			member = memberdbo.viewMember(memberId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return member;
	}
}
