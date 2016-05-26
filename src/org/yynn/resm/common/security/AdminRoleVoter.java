package org.yynn.resm.common.security;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>
 * Title: AdminRoleVoter.java
 * </p>
 * <p>
 * Description: ����ԱȨ�޴�����
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 6, 2007
 */
public class AdminRoleVoter implements AccessDecisionVoter, InitializingBean {
	private String adminCode;

	/*
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(org.acegisecurity.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute config) {
		return true;
	}

	/*
	 * @see org.acegisecurity.vote.AccessDecisionVoter#supports(java.lang.Class)
	 */
	public boolean supports(Class classzz) {
		return true;
	}

	/*
	 * @see org.acegisecurity.vote.AccessDecisionVoter#vote(org.acegisecurity.Authentication, java.lang.Object,
	 *      org.acegisecurity.ConfigAttributeDefinition)
	 */
	public int vote(Authentication authentication, Object obj, ConfigAttributeDefinition definition) {
		/**
		 * ����ǰ�û�ӵ��ϵͳ����Ȩ�ޣ���Ͷͬ��Ʊ�����������󣬷�����Ȩ��
		 */
		if (CurrentUserInfoProvider.hasAuth(adminCode)) return AccessDecisionVoter.ACCESS_GRANTED;

		return AccessDecisionVoter.ACCESS_ABSTAIN;
	}

	/*
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(adminCode, "adminCode required.");
	}

	/**
	 * @return the adminCode
	 */
	public String getAdminCode() {
		return adminCode;
	}

	/**
	 * @param adminCode the adminCode to set
	 */
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}

}
