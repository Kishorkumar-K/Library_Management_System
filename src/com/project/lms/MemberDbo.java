/**
 * interface for member database operation
 */
package com.project.lms;
import java.sql.SQLException;

public interface MemberDbo {

	public String addMember(Member member) throws SQLException;
	public String deleteMember(String memberId) throws SQLException;
	public Member viewMember(String memberId) throws SQLException;
	public int checkDueAmount(String memberid) throws SQLException;
	public String updateDueAmount(String memberid, int dueamt) throws SQLException;
}
