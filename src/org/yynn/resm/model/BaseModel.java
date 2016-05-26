package org.yynn.resm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title: BaseModel.java
 * </p>
 * <p>
 * Description: 公用属性,无数据表对应列.
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public class BaseModel implements Serializable {
  public static final int DELETEFLAG_DELETED = 1;

  /**
   * 4 query
   */
  private Date qbDate;

  private Date qeDate;

  /**
   * 4 Image 1 means delete, 0 means nothing.
   */
  private int deleteFlag = 0;

  /**
   * @return the deleteFlag
   */
  public int getDeleteFlag() {
    return deleteFlag;
  }

  /**
   * @param deleteFlag
   *          the deleteFlag to set
   */
  public void setDeleteFlag(int deleteFlag) {
    this.deleteFlag = deleteFlag;
  }

  /**
   * @return the qbDate
   */
  public Date getQbDate() {
    return qbDate;
  }

  /**
   * @param qbDate
   *          the qbDate to set
   */
  public void setQbDate(Date qbDate) {
    this.qbDate = qbDate;
  }

  /**
   * @return the qeDate
   */
  public Date getQeDate() {
    return qeDate;
  }

  /**
   * @param qeDate
   *          the qeDate to set
   */
  public void setQeDate(Date qeDate) {
    this.qeDate = qeDate;
  }
}
