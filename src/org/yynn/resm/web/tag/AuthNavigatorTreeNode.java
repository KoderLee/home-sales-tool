package org.yynn.resm.web.tag;

/**
 * <p>
 * Title: AuthNavigatorTreeNode
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-10
 */
public class AuthNavigatorTreeNode extends NavigatorTreeNode {
	private String[] requiredCodes;

	/**
	 * @return the requiredCodes
	 */
	public String[] getRequiredCodes() {
		return requiredCodes;
	}

	/**
	 * @param requiredCodes the requiredCodes to set
	 */
	public void setRequiredCodes(String[] requiredCodes) {
		this.requiredCodes = requiredCodes;
	}

	public void setRequiredCode(String requiredCode) {
		requiredCodes = new String[] { requiredCode };
	}

}
