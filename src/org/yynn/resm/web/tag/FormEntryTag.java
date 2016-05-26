package org.yynn.resm.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.yynn.resm.common.CommonDataProperty;
import org.yynn.resm.common.ICommonDataValue;
import org.yynn.resm.common.UiElementProperty;
import org.yynn.resm.common.util.CalendarUtil;
import org.yynn.resm.web.report.ReportQueryController;
import org.yynn.resm.web.spring.controller.AddController;
import org.yynn.resm.web.spring.controller.DeleteController;
import org.yynn.resm.web.spring.controller.EditController;
import org.yynn.resm.web.spring.controller.ModelBeanWrapperImpl;
import org.yynn.resm.web.spring.controller.QueryController;

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
public class FormEntryTag extends BaseTagSupport {

  private static final String DEFAULTSUBMITCODE = "common.submit";

  private static final String DEFAULTRESETCODE = "common.reset";

  private String submitCode = DEFAULTSUBMITCODE;

  private String resetCode = DEFAULTRESETCODE;

  private String titleCode;

  private int colNum = 2;

  private String modelValueName;

  private ModelBeanWrapperImpl modelAccesser;

  private String dateImagePath = "/images/dateico.gif";

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

  /*
   * (non-Javadoc)
   * 
   * @see org.yynn.resm.web.tag.BaseTagSupport#handleStartTag()
   */
  @Override
  protected int handleStartTag() throws JspException {

    if (null != modelValueName) {
      Object obj = getRequestAttribute(modelValueName);
      if (null != obj)
        modelAccesser = new ModelBeanWrapperImpl(obj);
      else
        modelAccesser = null;
    }
    ArrayList<UiElementProperty> uiElePropList = null;
    if (controllerType.isAssignableFrom(DeleteController.class)) {
      uiElePropList = uiPropertieProvider.getQueryFormProperties();
    } else if (controllerType.isAssignableFrom(QueryController.class)
        || controllerType.isAssignableFrom(ReportQueryController.class)) {
      uiElePropList = uiPropertieProvider.getQueryFormProperties();
    } else if (controllerType.isAssignableFrom(EditController.class)) {
      uiElePropList = uiPropertieProvider.getEditFormProperties();
    } else if (controllerType.isAssignableFrom(AddController.class)) {
      uiElePropList = uiPropertieProvider.getCreateFormProperties();
    } else
      return SKIP_BODY;

    StringBuffer content = new StringBuffer("");
    int colsinline = 0;
    generateTitle(content);
    for (UiElementProperty up : uiElePropList) {
      if (up.getDisplayType() != UiElementProperty.DISPLAYTYPE_HIDDEN) {
        if (colsinline == 0) {
          generateTrBegin(content);
        } else if ((colNum - colsinline) < up.getColspan()) {
          generateTrEnd(content);
          generateTrBegin(content);
          colsinline = 0;
        }
      }
      switch (up.getDisplayType()) {
        case UiElementProperty.DISPLAYTYPE_TEXT: {
          generatePlainText(content, up);
          colsinline = colsinline + up.getColspan();
          break;
        }
        case UiElementProperty.DISPLAYTYPE_PASSWORD: {
          generatePassword(content, up);
          colsinline = colsinline + up.getColspan();
          break;
        }
        case UiElementProperty.DISPLAYTYPE_HIDDEN: {
          generateHidden(content, up);
          break;
        }
        case UiElementProperty.DISPLAYTYPE_SELECT: {
          colsinline = colsinline + up.getColspan();
          generateSelect(content, up);
          break;
        }
        case UiElementProperty.DISPLAYTYPE_DATE: {
          colsinline = colsinline + up.getColspan();
          generateDate(content, up);
          break;
        }
        case UiElementProperty.DISPLAYTYPE_WITH_SUFFIX: {
          colsinline = colsinline + up.getColspan();
          generateWithSuffix(content, up);
          break;
        }
        case UiElementProperty.DISPLAYTYPE_MULTI_TEXT: {
          colsinline = colsinline + up.getColspan();
          generateMultiText(content, up);
          break;
        }
      }
    }
    generateTrEnd(content);
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
    content.append("<input type=text name=");
    String name = property.getCproperty().getName();
    content.append(name).append(" ");
    content.append("readonly=true class=box ");
    if (property.getSize() > 0) {
      content.append("size=").append(property.getSize()).append(" ");
    } else {
      content.append("size=10 ");
    }
    Date value = null;
    if (null != modelAccesser) {
      value = (Date) modelAccesser.getPropertyValue(name);
    } else if (null != property.getCproperty().getDefaultValue())
      value = (Date) property.getCproperty().getDefaultValue().getValue();

    if (null != value)
      content.append("value=").append(CalendarUtil.format(value, CalendarUtil.DAFAULTDATEFORMAT))
          .append(" ");

    content.append("/>&nbsp;<img align=absmiddle src=").append(servletRequest.getContextPath()).append(
        dateImagePath).append(" ");
    content.append("style=cursor:hand onclick=setday(this,document.all.").append(name).append(")>");
    generateErrorInfo(content, property);
    content.append("</td>");
  }

  private void generateSelect(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    CommonDataProperty cp = property.getCproperty();
    String name = cp.getName();
    if (property.isDisable()) {
      content.append("<input type=hidden name=");
      content.append(name).append(" ");
    } else {
      content.append("<select name=");
      content.append(name).append(" ");
      content.append("class=box >");
    }

    Object value = null;
    if (null != modelAccesser) {
      value = modelAccesser.getPropertyValue(name);
    } else if (null != property.getCproperty().getDefaultValue()) {
      value = cp.getDefaultValue().getValue();
    }

    ArrayList<ICommonDataValue> valueSet = cp.getValueSet();
    for (ICommonDataValue cdv : valueSet) {
      if (!property.isDisable()) {
        content.append("<option ");
        if (null != cdv.getValue()) {
          content.append("value=").append(cdv.getValue()).append(" ");
          if (cdv.getValue().equals(value)) {
            content.append("selected");
          }
        }
        String dvalue = cdv.getDisplayValue();

        content.append(">").append(
            null != dvalue ? dvalue : (null == cdv.getValue() ? " " : cdv.getValue()));
        content.append("</option>");
      } else {
        if (null != cdv.getValue() && cdv.getValue().equals(value)) {
          content.append("value=").append(cdv.getValue()).append(">");
          String dvalue = cdv.getDisplayValue();
          content.append(null != dvalue ? dvalue : (null == cdv.getValue() ? " " : cdv.getValue()));
        }
      }
    }
    if (!property.isDisable()) {
      content.append("</select>");
      generateErrorInfo(content, property);
    }
    content.append("</td>");
  }

  private void generateWithSuffix(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    content.append("<input type=text name=");
    content.append(property.getCproperty().getName()).append(" ");
    if (property.getSize() > 0)
      content.append("size=").append(property.getSize()).append(" ");
    if (property.getMaxLength() > 0)
      content.append("maxlength=").append(property.getMaxLength()).append(" ");
    if (property.isDisable()) {
      content.append("readonly=true class=readonly ");
    } else {
      content.append("class=box ");
    }
    if (null != modelAccesser) {
      Object value = modelAccesser.getPropertyValue(property.getCproperty().getName());
      if (null != value)
        content.append("value=").append(value.toString()).append(" ");
    } else if (null != property.getCproperty().getDefaultValue())
      content.append("value=").append(property.getCproperty().getDefaultValue().getValue()).append(" ");
    content.append(">");
    content.append(getMessage(property.getValueSuffix()));
    generateErrorInfo(content, property);
    content.append("</td>");
  }

  // private void generateImage(StringBuffer content, UiElementProperty
  // property) {
  // String title = getMessage(property.getDisplayNameCode());
  // content.append("<td nowrap class=head_v width=15%>");
  // content.append(title);
  // content.append("</td>");
  // content.append("<td class=text colspan=").append(property.getColspan() -
  // 1).append(">");
  // Object value = null;
  // if (null != modelAccesser)
  // value = modelAccesser.getPropertyValue(property.getCproperty().getName());
  //
  // content.append("<div id=_image_file_input_ ");
  // if (null != value)
  // content.append("style=display:none ");
  // content.append(">");
  // content.append("<input type=file name=");
  // content.append(property.getCproperty().getName()).append(" ");
  // if (property.getSize() > 0)
  // content.append("size=").append(property.getSize()).append(" ");
  // if (property.getMaxLength() > 0)
  // content.append("maxlength=").append(property.getMaxLength()).append(" ");
  // content.append("readonly=true class=readonly >");
  // content.append("</div>");
  //
  // if (null != value) {
  // // TODO
  // }
  //
  // generateErrorInfo(content, property);
  // content.append("</td>");
  // }

  private void generateMultiText(StringBuffer content, UiElementProperty property) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    content.append("<input type=textarea name=");
    content.append(property.getCproperty().getName()).append(" ");
    if (property.getTextRows() > 0)
      content.append("rows=").append(property.getTextRows()).append(" ");
    if (property.getSize() > 0)
      content.append("size=").append(property.getSize()).append(" ");
    if (property.getMaxLength() > 0)
      content.append("maxlength=").append(property.getMaxLength()).append(" ");
    if (property.isDisable()) {
      content.append("readonly=true class=readonly ");
    } else {
      content.append("class=box ");
    }
    if (null != modelAccesser) {
      Object value = modelAccesser.getPropertyValue(property.getCproperty().getName());
      if (null != value)
        content.append("value=").append(value.toString()).append(" ");
    } else if (null != property.getCproperty().getDefaultValue())
      content.append("value=").append(property.getCproperty().getDefaultValue().getValue()).append(" ");
    content.append(">");
    generateErrorInfo(content, property);
    content.append("</td>");
  }

  private void generateText(StringBuffer content, UiElementProperty property, String type) {
    String title = getMessage(property.getDisplayNameCode());
    content.append("<td nowrap class=head_v width=15%>");
    content.append(title);
    content.append("</td>");
    content.append("<td class=text colspan=").append(property.getColspan() - 1).append(">");
    content.append("<input type=").append(type).append(" name=");
    content.append(property.getCproperty().getName()).append(" ");
    if (property.getSize() > 0)
      content.append("size=").append(property.getSize()).append(" ");
    if (property.getMaxLength() > 0)
      content.append("maxlength=").append(property.getMaxLength()).append(" ");
    if (property.isDisable()) {
      content.append("readonly=true class=readonly ");
    } else {
      content.append("class=box ");
    }
    if (null != modelAccesser) {
      Object value = modelAccesser.getPropertyValue(property.getCproperty().getName());
      if (null != value)
        content.append("value=").append(value.toString()).append(" ");
    } else if (null != property.getCproperty().getDefaultValue())
      content.append("value=").append(property.getCproperty().getDefaultValue().getValue()).append(" ");
    content.append(">");
    generateErrorInfo(content, property);
    content.append("</td>");
  }

  private void generateErrorInfo(StringBuffer content, UiElementProperty property) {
    String pname = property.getCproperty().getName();
    BindException be = (BindException) getRequestAttribute(errorKey);
    if (null != be) {
      content.append("<font class=error>");
      List<FieldError> errors = be.getFieldErrors(pname);
      for (FieldError fe : errors) {
        content.append(getMessage(fe.getCode(), fe.getArguments()));
      }
      content.append("</font>");
    }
  }

  private void generatePlainText(StringBuffer content, UiElementProperty property) {
    generateText(content, property, "text");
  }

  private void generateHidden(StringBuffer content, UiElementProperty property) {
    Object value = null;
    if (null != property.getCproperty().getDefaultValue())
      value = property.getCproperty().getDefaultValue().getValue();
    if (null != modelAccesser) {
      value = modelAccesser.getPropertyValue(property.getCproperty().getName());
    }

    if (null == value)
      return;

    content.append("<input type=hidden name=");
    content.append(property.getCproperty().getName()).append(" ");

    if (value instanceof Date)
      content.append("value=").append(CalendarUtil.format((Date) value, CalendarUtil.DAFAULTDATEFORMAT))
          .append(" ");
    else
      content.append("value=").append(value.toString()).append(" ");

    content.append(">");
  }

  private void generatePassword(StringBuffer content, UiElementProperty property) {
    generateText(content, property, "password");
  }

  private void generateTrBegin(StringBuffer content) {
    content.append("<tr>");
  }

  private void generateTrEnd(StringBuffer content) {
    content.append("</tr>");
  }

  // private void generateTdBegin(StringBuffer content, UiElementProperty
  // property) {
  // content.append("<td colspan=").append(property.getColspan() - 1);
  // }
  //
  // private void generateTdEnd(StringBuffer content) {
  // content.append("</td>");
  // }

  private void generateSubmit(StringBuffer content) {
    content.append("<tr><td colspan=" + colNum + " nowrap align=center>");
    content.append("<input type=button value=" + getMessage(submitCode));
    content.append(" class=button onclick=this.form.submit()>");
    generateOtherAction(content);
    content.append("&nbsp;&nbsp;<input type=button value=" + getMessage(resetCode));
    content.append(" class=button onclick=this.form.reset()>");
    content.append("</td></tr>");
  }

  /**
   * Override by subclass...
   * 
   * @param content
   */
  protected void generateOtherAction(StringBuffer content) {

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
   * @return the resetCode
   */
  public String getResetCode() {
    return resetCode;
  }

  /**
   * @param resetCode
   *          the resetCode to set
   */
  public void setResetCode(String resetCode) {
    this.resetCode = resetCode;
  }

  /**
   * @return the submitCode
   */
  public String getSubmitCode() {
    return submitCode;
  }

  /**
   * @param submitCode
   *          the submitCode to set
   */
  public void setSubmitCode(String submitCodeKey) {
    this.submitCode = submitCodeKey;
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
  public void setTitleCode(String titileCode) {
    this.titleCode = titileCode;
  }

  /**
   * @return the dateImagePath
   */
  public String getDateImagePath() {
    return dateImagePath;
  }

  /**
   * @param dateImagePath
   *          the dateImagePath to set
   */
  public void setDateImagePath(String dateImagePath) {
    this.dateImagePath = dateImagePath;
  }

}
