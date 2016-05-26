package org.yynn.resm.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * Title: CommonDataValueProvider
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public interface ICommonDataValueSetProvider<PK extends Serializable> {
	public ArrayList<ICommonDataValue<PK>> getDataValueSet();

	public boolean isPreLoad();
}
