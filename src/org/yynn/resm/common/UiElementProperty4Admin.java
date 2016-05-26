package org.yynn.resm.common;

import java.io.Serializable;

import org.springframework.util.Assert;
import org.yynn.resm.common.security.CurrentUserInfoProvider;

/**
 * <p>
 * Title: UiElementProperty4Admin.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 11, 2007
 */
public class UiElementProperty4Admin<T extends Serializable> extends UiElementProperty<T> {
	private CommonDataProperty<T> cproperty4Admin;

	private int displayType4Admin = 0;

	public UiElementProperty4Admin(CommonDataProperty<T> cproperty, CommonDataProperty<T> cproperty4Admin) {
		super(cproperty);
		this.cproperty4Admin = cproperty4Admin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.common.UiElementProperty#getCproperty()
	 */
	@Override
	public CommonDataProperty<T> getCproperty() {
		if (isSysAdmin()) return this.cproperty4Admin;
		return super.getCproperty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.common.UiElementProperty#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		if (0 == this.displayType4Admin) this.displayType4Admin = super.getDisplayType();
		if (UiElementProperty.DISPLAYTYPE_HIDDEN != displayType4Admin)
			Assert.notNull(getDisplayNameCode(), "显式属性需要指定displayNameCode!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.common.UiElementProperty#getDisplayType()
	 */
	@Override
	public int getDisplayType() {
		if (isSysAdmin()) return this.displayType4Admin;
		return super.getDisplayType();
	}

	private boolean isSysAdmin() {
		return CurrentUserInfoProvider.hasAuth(CurrentUserInfoProvider.ROLE_ADMIN);
	}

	/**
	 * @param displayType4Admin the displayType4Admin to set
	 */
	public void setDisplayType4Admin(int displayType4Admin) {
		this.displayType4Admin = displayType4Admin;
	}
}
