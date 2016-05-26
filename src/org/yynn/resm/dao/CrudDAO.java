package org.yynn.resm.dao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * Title: CrudDAO.java
 * </p>
 * <p>
 * Description: CRUDDAO
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 17, 2007
 */
public interface CrudDAO<T, PK extends Serializable> {
	
	public Class<T> getModelType();
  
  public Class<PK> getPkType();
	
  /**
   * 加载主键为id的数据对象
   * @param id
   * @return
   */
	public T load(PK id);

  /**
   * 向数据库写入model
   * @param model
   * @return
   */
	public PK create(T model);

  /**
   * 修改数据中model对应的数据
   * @param model
   */
	public void update(T model);

	/**
   * 删除数据中id对应的数据 
	 * @param id
	 */
	public void delete(PK id);
	
  /**
   * 根据hsql语句查询数据
   * 
   * @param hsql
   * @return
   */
	public ArrayList<T> query(String hsql);
}
