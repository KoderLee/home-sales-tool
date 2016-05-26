package org.yynn.resm.common.security;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.yynn.resm.model.Project;

/**
 * <p>
 * Title: UserDetailImpl.java
 * </p>
 * <p>
 * Description: 封装Acegi所需的用户信息
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Feb 18, 2006
 */
class UserDetailImpl implements UserDetails {

	private Project user;

	private GrantedAuthority[] auths;

	public UserDetailImpl(Project user, String authProffix) {
		this.user = user;
		auths = new GrantedAuthorityImpl[] { new GrantedAuthorityImpl(authProffix + user.getLevel()) };
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		return auths;
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		return true;
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return user.getPassword();
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return user.getNo();
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {

		if (!(obj instanceof UserDetailImpl)) return false;

		UserDetailImpl tuser = (UserDetailImpl) obj;
		if (null == tuser.getUsername()) return false;

		return tuser.getUsername().equals(getUsername());
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int hashCode = 17;
		if (null != getUsername()) hashCode = 37 * hashCode + getUsername().hashCode();
		return hashCode;
	}

	/**
	 * @return the user
	 */
	public Project getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Project user) {
		this.user = user;
	}

}