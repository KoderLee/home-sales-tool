package org.yynn.resm.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * <p>
 * Title: CommonValueProviderConfigImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public class CommonValueProviderConfigImpl<PK extends Serializable> implements
    ICommonDataValueSetProvider<PK>, MessageSourceAware, InitializingBean {
  private MessageSource messageSource;

  private boolean isPreLoad = true;

  private Locale locale = Locale.SIMPLIFIED_CHINESE;

  private ArrayList<ICommonDataValue<PK>> dataValueList;

  /*
   * @see org.yynn.resm.common.ICommonDataValueSetProvider#getDataValueSet()
   */
  public ArrayList<ICommonDataValue<PK>> getDataValueSet() {
    return dataValueList;
  }

  public CommonValueProviderConfigImpl(ArrayList<ICommonDataValue<PK>> dataValueList) {
    this.dataValueList = dataValueList;
  }

  /**
   * @return the isPreLoad
   */
  public boolean isPreLoad() {
    return isPreLoad;
  }

  /**
   * @param isPreLoad
   *          the isPreLoad to set
   */
  public void setPreLoad(boolean isPreLoad) {
    this.isPreLoad = isPreLoad;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  public void afterPropertiesSet() throws Exception {
    ArrayList<ICommonDataValue<PK>> plist = new ArrayList<ICommonDataValue<PK>>();
    for (ICommonDataValue icdv : dataValueList) {
      Object value = icdv.getValue();
      String displayValue = icdv.getDisplayValue();
      if (displayValue != null)
        plist.add(new CommonDataValue(value, messageSource.getMessage(displayValue, null, locale)));
      else
        plist.add(new CommonDataValue(value));

    }
    this.dataValueList = plist;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.context.MessageSourceAware#setMessageSource(org.springframework.context.MessageSource)
   */
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;

  }

  /**
   * @return the locale
   */
  public Locale getLocale() {
    return locale;
  }

  /**
   * @param locale
   *          the locale to set
   */
  public void setLocale(Locale locale) {
    this.locale = locale;
  }

}
