package org.yynn.resm.service.impl;

import org.yynn.resm.dao.CrudDAO;
import org.yynn.resm.model.Customer;
import org.yynn.resm.model.Saleinfo;
import org.yynn.resm.model.Visitcustomer;

/**
 * <p>
 * Title: VisitCustomerCrudServiceImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 20, 2007
 */
public class VisitCustomerCrudServiceImpl extends CrudServiceImpl {
	public static final String DEFAULTSALEID = "saleinfo.id";

	public static final String DEFAULTCUSTOMERID = "customer.id";

	private CrudDAO<Saleinfo, Integer> saleDao;

	private CrudDAO<Customer, Integer> customerDao;

	private String saleIdKey = DEFAULTSALEID;

	private String customerIdKey = DEFAULTCUSTOMERID;

	private CrudDAO<Visitcustomer, Integer> visitCustomerDao;

	public VisitCustomerCrudServiceImpl(CrudDAO<Visitcustomer, Integer> visitCustomerDao,
			CrudDAO<Saleinfo, Integer> saleDao, CrudDAO<Customer, Integer> customerDao) {
		super(visitCustomerDao);
		this.visitCustomerDao = visitCustomerDao;
		this.saleDao = saleDao;
		this.customerDao = customerDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.service.impl.CrudServiceImpl#create(java.lang.Object)
	 */
	public Integer create(Visitcustomer model) {
		model.getCustomer().setId(customerDao.create(model.getCustomer()));
		if (isDeal(model)) {
			model.getSaleinfo().setId(saleDao.create(model.getSaleinfo()));
		}
		return visitCustomerDao.create(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.service.impl.CrudServiceImpl#delete(java.io.Serializable)
	 */
	public void delete(Integer id) {
		Visitcustomer vc = visitCustomerDao.load(id);
		Integer sid = vc.getSaleinfo().getId();
		Integer cid = vc.getCustomer().getId();
		visitCustomerDao.delete(id);
		if (isDeal(vc)) saleDao.delete(sid);
		customerDao.delete(cid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.service.impl.CrudServiceImpl#update(java.lang.Object)
	 */
	public void update(Visitcustomer model) {
		customerDao.update(model.getCustomer());
		if (isDeal(model)) saleDao.update(model.getSaleinfo());
		visitCustomerDao.update(model);
	}

	/**
	 * @return the customerIdKey
	 */
	public String getCustomerIdKey() {
		return customerIdKey;
	}

	/**
	 * @param customerIdKey the customerIdKey to set
	 */
	public void setCustomerIdKey(String customerIdKey) {
		this.customerIdKey = customerIdKey;
	}

	/**
	 * @return the saleIdKey
	 */
	public String getSaleIdKey() {
		return saleIdKey;
	}

	/**
	 * @param saleIdKey the saleIdKey to set
	 */
	public void setSaleIdKey(String saleIdKey) {
		this.saleIdKey = saleIdKey;
	}

	private boolean isDeal(Visitcustomer model) {
		return "1".equals(model.getDealornot());
	}
}
