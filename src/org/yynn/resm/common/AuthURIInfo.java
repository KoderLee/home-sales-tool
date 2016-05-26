package org.yynn.resm.common;


/**
 * <p>
 * Title: AuthURIInfo.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 12, 2007
 */
public class AuthURIInfo extends CommonURIInfo {
	private String[] requiredAuthCodes;

	/**
	 * @return the requiredAuthCodes
	 */
	public String[] getRequiredAuthCodes() {
		return requiredAuthCodes;
	}

	/**
	 * @param requiredAuthCodes the requiredAuthCodes to set
	 */
	public void setRequiredAuthCodes(String[] requiredAuthCodes) {
		this.requiredAuthCodes = requiredAuthCodes;
	}

	/**
	 * @param requiredAuthCodes the requiredAuthCodes to set
	 */
	public void setRequiredAuthCode(String requiredAuthCode) {
		this.requiredAuthCodes = new String[] { requiredAuthCode };
	}
}
