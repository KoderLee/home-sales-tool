package org.yynn.resm.web.filter.validator;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;

/**
 * <p>
 * Title: CommonValidator.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public class CommonValidator<T> implements Validator {
  private Class<T> type;

  private ArrayList<CommonValidationRule> rulers;

  public CommonValidator(Class<T> type) {
    this.type = type;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return type.isAssignableFrom(clazz);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.validation.Validator#validate(java.lang.Object,
   *      org.springframework.validation.Errors)
   */
  public void validate(Object target, Errors errors) {
    for (CommonValidationRule ruler : rulers) {
      ruler.validate(new ModelBeanWrapperImpl(target), errors);
    }
  }

  /**
   * @return the rulers
   */
  public ArrayList<CommonValidationRule> getRulers() {
    return rulers;
  }

  /**
   * @param rulers
   *          the rulers to set
   */
  public void setRulers(ArrayList<CommonValidationRule> rulers) {
    this.rulers = rulers;
  }

  /**
   * @return the type
   */
  public Class<T> getType() {
    return type;
  }

}
