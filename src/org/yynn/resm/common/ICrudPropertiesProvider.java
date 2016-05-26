package org.yynn.resm.common;

import java.util.ArrayList;

/**
 * <p>
 * Title: ICrudPropertiesProvider
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-5
 */
public interface ICrudPropertiesProvider {
  public ArrayList<QueryDataProperty> getQueryDataProperties();

  public ArrayList<UiElementProperty> getQueryFormProperties();

  public ArrayList<UiElementProperty> getQueryListProperties();
  
  public ArrayList<UiElementProperty> getCreateFormProperties();
  
  public ArrayList<UiElementProperty> getViewFormProperties();
  
  public ArrayList<UiElementProperty> getEditFormProperties();
  
  public UiElementProperty getPkProperty();
}
