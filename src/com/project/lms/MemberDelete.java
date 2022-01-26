/**
 * 
 */
package com.project.lms;

/**
 * @author kisho
 *
 */
public class MemberDelete {

	public String deleteMember(String memberId) {
		String result="";
		
		MemberDbo memberdbo = new MemberDbImpl();
		try {
			Member member = memberdbo.viewMember(memberId);
			if(member==null) {
				result = "Member Details not Found";
			}
			else {
				result = memberdbo.deleteMember(memberId);
			}
		}
		catch(Exception e) {
			result = "Member Deletion Failed";
		}
		
		return result;
	}
}
