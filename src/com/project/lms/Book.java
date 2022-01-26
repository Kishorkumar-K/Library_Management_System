/**
 * its a wrapper class for book related variables
 */
package com.project.lms;

/**
 * @author kisho
 *
 */
public class Book {

	private String memberid;
	private String bookid;
	private String title;
	private String author;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the memberid
	 */
	public String getMemberid() {
		return memberid;
	}
	/**
	 * @param memberid the memberid to set
	 */
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	/**
	 * @return the bookid
	 */
	public String getBookid() {
		return bookid;
	}
	/**
	 * @param bookid the bookid to set
	 */
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
}
