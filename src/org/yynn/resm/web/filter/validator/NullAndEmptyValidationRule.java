package org.yynn.resm.web.filter.validator;

import org.yynn.resm.common.UiElementProperty;

/**
 * <p>
 * Title: NullAndEmptyValidationRule.java
 * </p>
 * <p>
 * Description: 字符串非空校验规则
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class NullAndEmptyValidationRule extends AbstractValidationRule {

  public NullAndEmptyValidationRule(UiElementProperty property) {
    super(property);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.filter.validator.AbstractValidationRule#isValidate(java.lang.Object)
   */
  @Override
  protected boolean isValidate(Object value) {
    if (null == value)
      return false;

    if (value instanceof String && 0 == ((String) value).trim().length())
      return false;

    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.filter.validator.AbstractValidationRule#support(java.lang.Class)
   */
  @Override
  protected boolean support(Class clazz) {
    return true;
  }

}
