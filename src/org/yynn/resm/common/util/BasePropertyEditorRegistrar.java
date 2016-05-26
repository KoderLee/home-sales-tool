package org.yynn.resm.common.util;

import java.beans.PropertyEditor;
import java.util.HashMap;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * <p>
 * Title: BasePropertyEditorRegistrar.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 8, 2007
 */
public class BasePropertyEditorRegistrar implements PropertyEditorRegistrar {
	private HashMap<Class, PropertyEditor> propertyEditorMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors(org.springframework.beans.PropertyEditorRegistry)
	 */
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		for (Class type : propertyEditorMap.keySet()) {
			registry.registerCustomEditor(type, propertyEditorMap.get(type));
		}
	}

	public BasePropertyEditorRegistrar(HashMap<Class, PropertyEditor> propertyEditorMap) {
		this.propertyEditorMap = propertyEditorMap;
	}

}
