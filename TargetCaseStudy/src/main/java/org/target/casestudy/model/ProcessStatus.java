package org.target.casestudy.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Last Modified: June 10, 2016
 * @author Asad Islam
 *
 */
@XmlRootElement(name="process_status")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessStatus {
	String is_ready;
	String operation_Description;
	String openrationCode;
	/**
	 * @return the is_ready
	 */
	public String getIs_ready() {
		return is_ready;
	}
	/**
	 * @param is_ready the is_ready to set
	 */
	public void setIs_ready(String is_ready) {
		this.is_ready = is_ready;
	}
	/**
	 * @return the operation_Description
	 */
	public String getOperation_Description() {
		return operation_Description;
	}
	/**
	 * @param operation_Description the operation_Description to set
	 */
	public void setOperation_Description(String operation_Description) {
		this.operation_Description = operation_Description;
	}
	/**
	 * @return the openrationCode
	 */
	public String getOpenrationCode() {
		return openrationCode;
	}
	/**
	 * @param openrationCode the openrationCode to set
	 */
	public void setOpenrationCode(String openrationCode) {
		this.openrationCode = openrationCode;
	}
	
	

}
