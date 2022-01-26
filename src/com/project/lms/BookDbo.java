/**
 * 
 */
package com.project.lms;

import java.sql.SQLException;

/**
 * @author kisho
 *
 */
public interface BookDbo {

	public int checkDueDate(String memberid, String bookId) throws SQLException;
	public int fineAmount(int day) throws SQLException;
	public String updateStatus(String memberid, String bookid) throws SQLException;
	public String updateBookAvailableCount(String bookid) throws SQLException;
	
}
