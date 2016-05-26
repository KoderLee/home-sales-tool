package org.yynn.resm.web.spring.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.yynn.resm.common.AuthURIInfo;
import org.yynn.resm.common.CommonURIInfo;
import org.yynn.resm.common.security.CurrentUserInfoProvider;

/**
 * <p>
 * Title: AuthBarViewController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 12, 2007
 */
public class AuthBarViewController extends CommonViewController {
	public static final String DEFAULT_URIINFOLIST_KEY = "uriInfoList";

	private String uriInfoListKey = DEFAULT_URIINFOLIST_KEY;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> attributes = getAttributes();
		if (null != attributes) {
			for (String key : attributes.keySet()) {
				if (uriInfoListKey.equals(key))
					request.setAttribute(key, filterUriList((ArrayList<CommonURIInfo>) attributes.get(key)));
				else
					request.setAttribute(key, attributes.get(key));
			}
		}
		return new ModelAndView(getViewName());
	}

	private ArrayList<CommonURIInfo> filterUriList(ArrayList<CommonURIInfo> source) {
		ArrayList<CommonURIInfo> result = new ArrayList<CommonURIInfo>();
		for (CommonURIInfo cui : source) {
			if (cui instanceof AuthURIInfo && !(CurrentUserInfoProvider.hasAuth(((AuthURIInfo) cui).getRequiredAuthCodes()))) {
				continue;
			}
			else
				result.add(cui);
		}
		return result;
	}

	/**
	 * @return the uriInfoListKey
	 */
	public String getUriInfoListKey() {
		return uriInfoListKey;
	}

	/**
	 * @param uriInfoListKey the uriInfoListKey to set
	 */
	public void setUriInfoListKey(String uriInfoListKey) {
		this.uriInfoListKey = uriInfoListKey;
	}
}
