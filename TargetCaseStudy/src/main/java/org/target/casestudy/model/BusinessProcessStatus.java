package org.target.casestudy.model;

import java.util.List;
/**
 * 
 * @author Asad Islam
 * Last Updated: June 19, 2016
 *
 */
public class BusinessProcessStatus {
	List<ProcessStatus> processStatus;

	/**
	 * @return the processStatus
	 */
	public List<ProcessStatus> getProcessStatus() {
		return processStatus;
	}

	/**
	 * @param processStatus the processStatus to set
	 */
	public void setProcessStatus(List<ProcessStatus> processStatus) {
		this.processStatus = processStatus;
	}
}
