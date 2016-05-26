package org.yynn.resm.common;

import java.io.Serializable;

/**
 * <p>
 * Title: QueryDataEntry.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class QueryDataEntry {
  private QueryDataProperty property;

  private Serializable value;

  public QueryDataEntry(QueryDataProperty property, Serializable value) {
    this.property = property;
    this.value = value;
  }

  public String generateHql() {
    return property.geratorQueryString(value);
  }
}
