package org.yynn.resm.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.jsp.JspException;

import org.yynn.resm.common.ICommonDataValue;
import org.yynn.resm.common.UiElementProperty;
import org.yynn.resm.common.util.CalendarUtil;
import org.yynn.resm.web.spring.controller.AbstractCRUDController;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;
import org.yynn.resm.web.spring.controller.ViewController;

/**
 * <p>
 * Title: FormEntryTag.java
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created May 29, 2007
 */
public class ModelViewEntryTag extends BaseTagSupport {

  private static final String DEFAULTSUBMITCODE = "common.modify";

  private static final String DEFAULTRESETCODE = "common.return";

  private String modifyCode = DEFAULTSUBMITCODE;

  private String returnCode = DEFAULTRESETCODE;

  private String deleteCode = "common.delete";

  private String titleCode = "common.viewdata";

  private String modifyPath;

  private String returnPath;

  private String deletePath;

  private String readFailCode = "common.read.fail";

  private String modelValueName = ViewController.DEFAULT_MODELVALUE_NAME;

  private int colNum = 2;

  private ModelBeanWrapperImpl modelAccesser;

  private String queryObjectName = ViewController.DEFAULT_QUERYOBJECT_NAME;

  public static final String HIDDENQUERYFORMID = "hiddenQueryForm";

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.tag.BaseTagSupport#handleStartTag()
   */
  @Override
  protected int handleStartTag() throws JspException {
    StringBuffer content = new StringBuffer("");

    Object obj = getRequestAttribute(modelValueName);
    generateTitle(content);

    if (null == obj) {
      generateTrBegin(content);
      content.append("<td class=result_info>");
      content.append(getMessage(readFailCode));
      content.append("</td");
      generateTrEnd(content);
    } else {
      modelAccesser = new ModelBeanWrapperImpl(obj);

      ArrayList<UiElementProperty> uiElePropList = uiPropertieProvider.getViewFormProperties();

      int colsinline = 0;

      for (UiElementProperty up : uiElePropList) {
        if (up.getDisplayType() == UiElementProperty.DISPLAYTYPE_HIDDEN)
          continue;

        if (colsinline == 0) {
          generateTrBegin(content);
        } else if ((colNum - colsinline) < up.getColspan()) {
          generateTrEnd(content);
          generateTrBegin(content);
          colsinline = 0;
        }
        switch (up.getDisplayType()) {
          case UiElementProperty.DISPLAYTYPE_TEXT: {
            generateText(content, up);
            colsinline = colsinline + up.getColspan();
            break;
          }
          case UiElementProperty.DISPLAYTYPE_PASSWORD: {
            generatePassword(content, up);
            colsinline = colsinline + up.getColspan();
            break;
          }
          case UiElementProperty.DISPLAYTYPE_HIDDEN: {
            break;
          }
          case UiElementProperty.DISPLAYTYPE_SELECT: {
            generateSelect(content, up);
            colsinline = colsinline + up.getColspan();
            break;
          }
          case UiElementProperty.DISPLAYTYPE_DATE: {
            generateDate(content, up);
            colsinline = colsinline + up.getColspan();
            break;
          }
          case UiElementProperty.DISPLAYTYPE_WITH_SUFFIX: {
            generateWithSuffix(content, up);
            colsinline = colsinline + up.getColspan();
            break;
          }
          case UiElementProperty.DISPLAYTYPE_MULTI_TEXT: {
            generateText(content, up);
            colsinline = colsinline + up.getColspan();
            break;
          }
        }
      }
      generateTrEnd(content);
    }

    generateSubmit(content);
    try {
      pageContext.getOut().println(content.toString());
    } catch (IOException ex) {
      throw new JspException("Can't write with JspWriter", ex);
    }
    return SKIP_BODY;
  }

  private void generateDate(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    String pname = property.getCproperty().getName();
    Object value = modelAccesser.getPropertyValue(pname);
    if (null != value)
      content.append(CalendarUtil.format((Date) value, CalendarUtil.DAFAULTDATEFORMAT));
    content.append("</td>");

  }

  private void generateSelect(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    String pname = property.getCproperty().getName();
    Object value = modelAccesser.getPropertyValue(pname);

    ArrayList<ICommonDataValue> valueList = property.getCproperty().getValueSet();

    String displayValue = null == value ? "" : value.toString();

    for (ICommonDataValue idv : valueList) {
      if (null != idv.getValue() && idv.getValue().equals(value) && null != idv.getDisplayValue())
        displayValue = idv.getDisplayValue();
    }
    content.append(null == displayValue ? "" : displayValue);
    content.append("</td>");
  }

  private void generateWithSuffix(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    String pname = property.getCproperty().getName();
    Object value = modelAccesser.getPropertyValue(pname);
    content.append(null == value ? "" : value);
    if (null != value)
      content.append(getMessage(property.getValueSuffix()));
    content.append("</td>");
  }

  private void generateText(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    String pname = property.getCproperty().getName();
    Object value = modelAccesser.getPropertyValue(pname);
    content.append(null == value ? "" : value);
    content.append("</td>");
  }

  private void generatePassword(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    content.append("******");
    content.append("</td>");
  }

  private void generateTrBegin(StringBuffer content) {
    content.append("<tr>");
  }

  private void generateTrEnd(StringBuffer content) {
    content.append("</tr>");
  }

  private void generateSubmit(StringBuffer content) {
    content.append("<form method=post name=").append(HIDDENQUERYFORMID).append(">");
    generateQueryEntry(content);
    content.append("</form>");
  }

  private void generateQueryEntry(StringBuffer content) {
    ArrayList<UiElementProperty> qprops = uiPropertieProvider.getQueryFormProperties();
    for (UiElementProperty up : qprops) {
      String name = up.getCproperty().getName();
      Object value = getParameter(name);
      content.append("<input type=hidden name=").append(name);
      content.append(" value=").append(value).append(">");

    }

    UiElementProperty pkUie = uiPropertieProvider.getPkProperty();
    String pkName = pkUie.getCproperty().getName();
    Object pkValue = modelAccesser.getPropertyValue(pkName);
    pkName = AbstractCRUDController.PK_PREFIX + pkName;
    content.append("<tr><td colspan=" + colNum + " nowrap align=center>");
    if (canModify()) {
      generateModify(content, pkName, pkValue);
      content.append("&nbsp;&nbsp;");
    }
    if (canDelete()) {
      generateDelete(content, pkName, pkValue);
      content.append("&nbsp;&nbsp;");
    }
    generateReturn(content);
    content.append("</td></tr>");
  }

  private void generateModify(StringBuffer content, String pkName, Object pkValue) {
    if (null == modifyPath)
      return;
    content.append("<input type=button value=" + getMessage(modifyCode));
    content.append(" class=button onclick=goForward('").append(HIDDENQUERYFORMID).append("','");
    content.append(modifyPath);
    if (-1 == "?".indexOf(modifyPath))
      content.append("?");
    else
      content.append("&");
    content.append(pkName).append("=");
    content.append(pkValue);
    content.append("')>");
  }

  private void generateReturn(StringBuffer content) {
    if (null == returnPath)
      return;
    content.append("<input type=button value=" + getMessage(returnCode));
    content.append(" class=button onclick=goForward('").append(HIDDENQUERYFORMID).append("','");
    content.append(returnPath);
    content.append("')>");
  }

  private void generateDelete(StringBuffer content, String pkName, Object pkValue) {
    if (null == deletePath)
      return;
    content.append("<input type=button value=" + getMessage(deleteCode));
    content.append(" class=button onclick=goDelete('").append(HIDDENQUERYFORMID).append("','");
    content.append(deletePath);
    if (-1 == "?".indexOf(deletePath))
      content.append("?");
    else
      content.append("&");
    content.append(pkName).append("=");
    content.append(pkValue);
    content.append("')>");
  }

  private void generateTitle(StringBuffer content) {
    content.append("<tr><td colspan=" + colNum + " class=title>");
    content.append(getMessage(titleCode));
    content.append("</td></tr>");
  }

  /**
   * @return the colNum
   */
  public int getColNum() {
    return colNum;
  }

  /**
   * @param colNum
   *          the colNum to set
   */
  public void setColNum(int colNum) {
    this.colNum = colNum;
  }

  /**
   * @return the returnCode
   */
  public String getResetCodeKey() {
    return returnCode;
  }

  /**
   * @param returnCode
   *          the returnCode to set
   */
  public void setReturnCode(String resetCodeKey) {
    this.returnCode = resetCodeKey;
  }

  /**
   * @return the modifyCode
   */
  public String getSubmitCodeKey() {
    return modifyCode;
  }

  /**
   * @param modifyCode
   *          the modifyCode to set
   */
  public void setModifyCode(String submitCodeKey) {
    this.modifyCode = submitCodeKey;
  }

  /**
   * @return the titleCode
   */
  public String getTitleCode() {
    return titleCode;
  }

  /**
   * @param titleCode
   *          the titleCode to set
   */
  public void setTitleCode(String titileCodeKey) {
    this.titleCode = titileCodeKey;
  }

  /**
   * @return the readFailCode
   */
  public String getReadFailCode() {
    return readFailCode;
  }

  /**
   * @param readFailCode
   *          the readFailCode to set
   */
  public void setReadFailCode(String readFailCode) {
    this.readFailCode = readFailCode;
  }

  /**
   * @return the deletePath
   */
  public String getDeletePath() {
    return deletePath;
  }

  /**
   * @param deletePath
   *          the deletePath to set
   */
  public void setDeletePath(String deletePath) {
    this.deletePath = deletePath;
  }

  /**
   * @return the modelValueName
   */
  public String getModelValueName() {
    return modelValueName;
  }

  /**
   * @param modelValueName
   *          the modelValueName to set
   */
  public void setModelValueName(String modelValueName) {
    this.modelValueName = modelValueName;
  }

  /**
   * @return the modifyPath
   */
  public String getModifyPath() {
    return modifyPath;
  }

  /**
   * @param modifyPath
   *          the modifyPath to set
   */
  public void setModifyPath(String modifyPath) {
    this.modifyPath = modifyPath;
  }

  /**
   * @return the queryObjectName
   */
  public String getQueryObjectName() {
    return queryObjectName;
  }

  /**
   * @param queryObjectName
   *          the queryObjectName to set
   */
  public void setQueryObjectName(String queryObjectName) {
    this.queryObjectName = queryObjectName;
  }

  /**
   * @return the returnPath
   */
  public String getReturnPath() {
    return returnPath;
  }

  /**
   * @param returnPath
   *          the returnPath to set
   */
  public void setReturnPath(String returnPath) {
    this.returnPath = returnPath;
  }

  /**
   * @return the modifyCode
   */
  public String getModifyCode() {
    return modifyCode;
  }

  /**
   * @return the returnCode
   */
  public String getReturnCode() {
    return returnCode;
  }
}
