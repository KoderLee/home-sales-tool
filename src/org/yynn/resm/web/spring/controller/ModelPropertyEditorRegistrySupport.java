package org.yynn.resm.web.spring.controller;

import java.beans.PropertyEditor;

import org.springframework.beans.PropertyEditorRegistrySupport;

/**
 * <p>
 * Title: ModelPropertyEditorRegistrySupport.java
 * </p>
 * <p>
 * Description: 
 * </p>
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 9, 2007
 */
public class ModelPropertyEditorRegistrySupport extends PropertyEditorRegistrySupport {

	/* (non-Javadoc)
	 * @see org.springframework.beans.PropertyEditorRegistrySupport#getDefaultEditor(java.lang.Class)
	 */
	@Override
	protected PropertyEditor getDefaultEditor(Class requiredType) {
		// TODO Auto-generated method stub
		return super.getDefaultEditor(requiredType);
	}
	
}
