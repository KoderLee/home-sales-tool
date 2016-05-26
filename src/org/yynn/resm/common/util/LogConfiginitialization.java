package org.yynn.resm.common.util;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.yynn.resm.common.exception.InitializingException;

/**
 * <p>
 * Title: LogConfiginitialization.java
 * </p>
 * <p>
 * Description: ��־���ó�ʼ��,��ѡ
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created Mar 28, 2006
 */
public class LogConfiginitialization implements InitializingBean {

  private boolean initializeConfig = false;

  private String log4jConfigFileLocation;

  public static final String XMLSUFFIX = ".xml";

  public static final String PROPSUNFFIX = ".properties";

  public synchronized void init() throws InitializingException {
    if (!initializeConfig)
      return;
    try {
      ClassPathResource resource = new ClassPathResource(log4jConfigFileLocation);

      if (!resource.exists()) {
        resource = new ClassPathResource(".");
        resource.createRelative(log4jConfigFileLocation);
      }

      if (log4jConfigFileLocation.endsWith(XMLSUFFIX))
        DOMConfigurator.configure(resource.getURL());
      else if (log4jConfigFileLocation.endsWith(PROPSUNFFIX))
        PropertyConfigurator.configure(resource.getURL());
    } catch (IOException ex) {
      throw new InitializingException("��ʼ��Log4j����ʧ��.", ex);
    }
  }

  /**
   * @return ���� initializeConfig��
   */
  public boolean isInitializeConfig() {
    return initializeConfig;
  }

  /**
   * @param initializeConfig
   *          Ҫ�޸ĵ� initializeConfig��
   */
  public void setInitializeConfig(boolean initializeConfig) {
    this.initializeConfig = initializeConfig;
  }

  /**
   * @return ���� log4jConfigFileLocation��
   */
  public String getLog4jConfigFileLocation() {
    return log4jConfigFileLocation;
  }

  /**
   * @param log4jConfigFileLocation
   *          Ҫ�޸ĵ� log4jConfigFileLocation��
   */
  public void setLog4jConfigFileLocation(String log4jConfigFileLocation) {
    this.log4jConfigFileLocation = log4jConfigFileLocation;
  }

  public void afterPropertiesSet() throws Exception {
    if (initializeConfig)
      Assert.notNull(this.log4jConfigFileLocation, "log4jConfigFileLocation required.");
  }
}
