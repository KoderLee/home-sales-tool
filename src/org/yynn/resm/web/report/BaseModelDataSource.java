package org.yynn.resm.web.report;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.yynn.resm.model.BaseModel;

/**
 * <p>
 * Title: BaseModelDataSource.java
 * </p>
 * <p>
 * Description: 
 * </p>
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 22, 2007
 */
public class BaseModelDataSource implements JRDataSource {
	private ArrayList<BaseModel> modelList;
	private int index = -1;
	
	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
	 */
	public Object getFieldValue(JRField jrField) throws JRException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRDataSource#next()
	 */
	public boolean next() throws JRException {
		index++;

		return (index < modelList.size());
	}

}
