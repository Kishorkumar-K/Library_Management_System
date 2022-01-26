/**
 * 
 */
package com.project.lms;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author kisho
 *
 */
public class MemberDbImpl implements MemberDbo{

	conn con = new conn();
	@Override
	public String addMember(Member member) throws SQLException{
		String result = "";
		
		
		try {
			String sql1 = "SELECT member_id FROM member ORDER BY member_id DESC LIMIT 1";			// to get last member id stored in table and increments its value for new one
			PreparedStatement st1 = con.c.prepareStatement(sql1);
			java.sql.ResultSet rs1 = st1.executeQuery();
			if(rs1.next()) {
				int new_memid = Integer.parseInt(rs1.getString("member_id"));
				new_memid+=1;
				member.setMemberId(Integer.toString(new_memid));					//converting integer into string
			}
			else {
				System.out.println("Some wrong with fetching last member id");
			}
			
			
			String sql = "INSERT INTO member (member_id, member_name, address, proof_type, proof_id, due_amt, mobile_no) VALUES (?, ?, ?, ?, ?, ?, ?);";			//sql query to insert data in member table
			PreparedStatement st = con.c.prepareStatement(sql);
			st.setString(1, member.getMemberId());
			st.setString(2, member.getMemberName());
			st.setString(3, member.getAddress());
			st.setString(4, member.getProofType());
			st.setString(5, member.getProofId());
			st.setInt(6, 0);
			st.setString(7, member.getMobileNum());
			
			int ps = st.executeUpdate();
			if(ps > 0 ) {
				result = "Details Updated Successfully";
				System.out.println("Member ID : "+member.getMemberId());
			}
			else {
				result = "Adding Member Failed";
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
		return result;
		
	}
	
	
	@Override
	public Member viewMember(String memberId){
		Member member = null;
		try {
			String sql2 = "SELECT * FROM member where member_id = ?";			// query to display the member details based on member id
			
			PreparedStatement st2 = con.c.prepareStatement(sql2);
			st2.setString(1, memberId);
			java.sql.ResultSet rs2 = st2.executeQuery();
			member = new Member();
			while(rs2.next()) {
				member.setMemberId(rs2.getString("member_id"));
				member.setMemberName(rs2.getString("member_name"));
				member.setAddress(rs2.getString("address"));
				member.setMobileNum(rs2.getString("mobile_no"));
				member.setProofType(rs2.getString("proof_type"));
				member.setProofId(rs2.getString("proof_id"));
				member.setDueAmt(rs2.getInt("due_amt"));
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	
	@Override
	public String deleteMember(String memberId) {
		String result ="";
		conn con = new conn();
		try {
			String sql3 ="DELETE FROM member WHERE member_id = ?;";
			PreparedStatement st3 = con.c.prepareStatement(sql3);
			st3.setString(1, memberId);
			int check = st3.executeUpdate();
			if(check>0) {
				result = "Member Deleted Successfully";
			}
			else {
				result = "Member Details not Found";
			}
			con.c.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			result = "Member Deletion Failed";
		}
		return result;
	}
	
	public int checkDueAmount(String memberid) {
		
		try {
			String sql4 = "Select * from member where member_id = ?";
			PreparedStatement st = con.c.prepareStatement(sql4);
			st.setString(1, memberid);
			java.sql.ResultSet rs4 = st.executeQuery();
			if(rs4.next()) {
				if(rs4.getInt("due_amt")==0) {
					System.out.println("\t\t\t\tNo Due Amount Found!");
					return 0;
				}
				else if(rs4.getInt("due_amt")>0){	
					return rs4.getInt("due_amt");						//if due amount found means it returns the value
				}
			}
			else {
				System.out.println("No data Returned from Database");
			}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public String updateDueAmount(String memberid, int dueamt) {
		
		String result = "";
		conn con = new conn();
		
		try {
			
			String sql5 = "UPDATE member SET due_amt=? WHERE member_id = ?";			//query to update due amount in member table
			PreparedStatement st5 = con.c.prepareStatement(sql5);
			st5.setInt(1, dueamt);
			st5.setString(2, memberid);
			st5.executeUpdate();
			result = "Due Amount Updated Successfully";
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			result = "Some problem with update due amount method";
		}
		return result;
	}
	
}
