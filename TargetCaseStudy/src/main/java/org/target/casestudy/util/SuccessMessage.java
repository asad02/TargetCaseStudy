/**
 * 
 */
package org.target.casestudy.util;

/**
 * @author Asad Islam
 * last Updated: Feb 7, 2016
 */
public class SuccessMessage {
	private int successCode;
	private String successMessage;
	/**
	 * @return the successCode
	 */
	public int getSuccessCode() {
		return successCode;
	}
	/**
	 * @param successCode the successCode to set
	 */
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
	}
	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}
	/**
	 * @param successMessage the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	/**
	 * @param successCode
	 * @param successMessage
	 */
	public SuccessMessage(int successCode, String successMessage) {
		this.successCode = successCode;
		this.successMessage = successMessage;
	}
	/**
	 * 
	 */
	public SuccessMessage() {
		// TODO Auto-generated constructor stub
	}
	
}
