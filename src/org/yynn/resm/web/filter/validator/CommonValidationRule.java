package org.yynn.resm.web.filter.validator;

import org.springframework.beans.PropertyAccessor;
import org.springframework.validation.Errors;

/**
 * <p>
 * Title: CommonValidationRule.java
 * </p>
 * <p>
 * Description: 
 * </p>
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 23, 2007
 */
public interface CommonValidationRule {
	public void validate(PropertyAccessor target, Errors errors);
}
