package org.yynn.resm.common.security;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;
import org.yynn.resm.common.exception.HibernateDataAccessException;
import org.yynn.resm.dao.CrudDAO;
import org.yynn.resm.model.Project;

/**
 * <p>
 * Title: UserDetailProvider.java
 * </p>
 * <p>
 * Description: 提供Acegi所需的用户信息
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Feb 17, 2006
 */
public class UserDetailProvider<T, Serializable> implements UserDetailsService, InitializingBean {

	private CrudDAO<Project, String> userDao;

	private String authPreffix = "auth_";

	public UserDetailProvider(CrudDAO<Project, String> userDao) {
		this.userDao = userDao;
	}

	/*
	 * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException,
			DataAccessException {
		/*
		 * 加载用户
		 */
		Project user = null;
		user = userDao.load(userName);
		UserDetailImpl adapter = null;
		try {
			adapter = new UserDetailImpl(user, authPreffix);
		}
		catch (ObjectNotFoundException ex) {
			throw new UsernameNotFoundException("UserName Not Found:[" + userName + "]", ex);
		}
		catch (HibernateException ex) {
			throw new HibernateDataAccessException("数据库访问异常");
		}
		
		return adapter;
	}

	/*
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userDao, "userDao required.");
	}

	/**
	 * @return the authPreffix
	 */
	public String getAuthPreffix() {
		return authPreffix;
	}

	/**
	 * @param authPreffix the authPreffix to set
	 */
	public void setAuthPreffix(String authPreffix) {
		this.authPreffix = authPreffix;
	}
}
