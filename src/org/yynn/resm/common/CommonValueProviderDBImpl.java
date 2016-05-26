package org.yynn.resm.common;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.yynn.resm.dao.CrudDAO;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;

/**
 * <p>
 * Title: CommonValueProviderDBImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public class CommonValueProviderDBImpl<T, PK extends Serializable> implements ICommonDataValueSetProvider<PK>,
		InitializingBean {
	private CrudDAO<T, PK> crudDao;

	private String pkName;

	private String displayName;

	private String hsqlPreffix;

	private String asName = "model";

	private String queryAppend;

	private boolean isPreLoad = false;

	/**
	 * @return the isPreLoad
	 */
	public boolean isPreLoad() {
		return isPreLoad;
	}

	/**
	 * @param isPreLoad the isPreLoad to set
	 */
	public void setPreLoad(boolean isPreLoad) {
		this.isPreLoad = isPreLoad;
	}

	public CommonValueProviderDBImpl(CrudDAO<T, PK> crudDao) {
		this.crudDao = crudDao;
	}

	/*
	 * @see org.yynn.resm.common.CommonDataValueProvider#getDataValueSet()
	 */
	public ArrayList<ICommonDataValue<PK>> getDataValueSet() {
		StringBuffer qhsql = new StringBuffer(hsqlPreffix);
		if (null != queryAppend) qhsql.append("and ").append(queryAppend);
		ArrayList<T> modelList = crudDao.query(qhsql.toString());
		ArrayList<ICommonDataValue<PK>> cdvSet = new ArrayList<ICommonDataValue<PK>>();
		for (T t : modelList) {
			cdvSet.add(getCommonDataValue(t));
		}
		return cdvSet;
	}

	protected ICommonDataValue<PK> getCommonDataValue(T t) {
		ModelBeanWrapperImpl bwi = new ModelBeanWrapperImpl(t);
		PK value = (PK) bwi.getPropertyValue(pkName);
		if (null != displayName) return new CommonDataValue<PK>(value, (String) bwi.getPropertyValue(displayName));
		return new CommonDataValue<PK>(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(pkName, "pkName required!");
		if (null == hsqlPreffix) {
			StringBuffer qhsql = new StringBuffer("from ");
			qhsql.append(crudDao.getModelType().getName());
			qhsql.append(" as ").append(asName);
			qhsql.append(" where (1=1) ");

			this.hsqlPreffix = qhsql.toString();
		}
	}

	/**
	 * @return the asName
	 */
	public String getAsName() {
		return asName;
	}

	/**
	 * @param asName the asName to set
	 */
	public void setAsName(String asName) {
		this.asName = asName;
	}

	/**
	 * @return the crudDao
	 */
	public CrudDAO<T, PK> getCrudDao() {
		return crudDao;
	}

	/**
	 * @param crudDao the crudDao to set
	 */
	public void setCrudDao(CrudDAO<T, PK> crudDao) {
		this.crudDao = crudDao;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the hsqlPreffix
	 */
	public String getHsqlPreffix() {
		return hsqlPreffix;
	}

	/**
	 * @param hsqlPreffix the hsqlPreffix to set
	 */
	public void setHsqlPreffix(String hsqlPreffix) {
		this.hsqlPreffix = hsqlPreffix;
	}

	/**
	 * @return the pkName
	 */
	public String getPkName() {
		return pkName;
	}

	/**
	 * @param pkName the pkName to set
	 */
	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	/**
	 * @return the queryAppend
	 */
	public String getQueryAppend() {
		return queryAppend;
	}

	/**
	 * @param queryAppend the queryAppend to set
	 */
	public void setQueryAppend(String queryAppend) {
		this.queryAppend = queryAppend;
	}

}
