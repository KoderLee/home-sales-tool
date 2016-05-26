package org.yynn.resm.service;

import java.io.Serializable;
import java.util.ArrayList;

import org.yynn.resm.common.QueryDataEntry;

/**
 * <p>
 * Title: CrudService.java
 * </p>
 * <p>
 * Description: CRUD业务接口
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public interface CrudService<T, PK extends Serializable> {
	public Class<T> getModelType();
  
  public Class<PK> getPKType();

	public T read(PK id);

	public ArrayList<T> query(ArrayList<QueryDataEntry> qoList);

	public PK create(T model);

	public void delete(PK id);

	public void update(T model);
}
