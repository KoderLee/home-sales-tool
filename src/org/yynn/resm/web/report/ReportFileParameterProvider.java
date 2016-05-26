package org.yynn.resm.web.report;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;

/**
 * <p>
 * Title: ReportFileParameterProvider.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Jun 14, 2007
 */
public class ReportFileParameterProvider implements IReportParameterProvider, ServletContextAware, InitializingBean {
	private static final Logger logger = Logger.getLogger(ReportFileParameterProvider.class);

	private ServletContext serlvetContext;

	private String relativePath;

	private File file;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		file = new File(serlvetContext.getRealPath(relativePath));
		Assert.isTrue(file.exists(), "指定的路径不存在：" + relativePath);
		logger.info("Real File Path: " + file.getAbsolutePath());
	}

	public ReportFileParameterProvider(String relativePath) {
		this.relativePath = relativePath;
	}

	/**
	 * @return the relativePath
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.report.IReportParameterProvider#getReportParameter()
	 */
	public Object getReportParameter() {
		return this.file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext servletContext) {
		this.serlvetContext = servletContext;

	}
}
