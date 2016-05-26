package org.yynn.resm.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * Title: CommonValueProviderDBImpl
 * </p>
 * <p>
 * Description: 组合取值提供者
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-2
 */
public class CommonValueProviderCombineImpl<PK extends Serializable> implements
    ICommonDataValueSetProvider<PK> {
  private ArrayList<ICommonDataValueSetProvider> preLoadProviders = new ArrayList<ICommonDataValueSetProvider>();

  private ArrayList<ICommonDataValueSetProvider> lazyLoadProviders = new ArrayList<ICommonDataValueSetProvider>();

  private ArrayList<ICommonDataValue<PK>> dataValueList = new ArrayList<ICommonDataValue<PK>>();

  /**
   * @return the isPreLoad
   */
  public boolean isPreLoad() {
    return lazyLoadProviders.size() == 0;
  }

  public CommonValueProviderCombineImpl(ArrayList<ICommonDataValueSetProvider<PK>> providers) {

    for (ICommonDataValueSetProvider p : providers) {
      if (p.isPreLoad())
        preLoadProviders.add(p);
      else
        lazyLoadProviders.add(p);
    }

    for (ICommonDataValueSetProvider p : preLoadProviders) {
      dataValueList.addAll(p.getDataValueSet());
    }
  }

  /*
   * @see org.yynn.resm.common.CommonDataValueProvider#getDataValueSet()
   */
  public ArrayList<ICommonDataValue<PK>> getDataValueSet() {
    ArrayList<ICommonDataValue<PK>> rs = new ArrayList<ICommonDataValue<PK>>();
    rs.addAll(this.dataValueList);
    for (ICommonDataValueSetProvider p : lazyLoadProviders) {
      rs.addAll(p.getDataValueSet());
    }
    return rs;
  }
}
