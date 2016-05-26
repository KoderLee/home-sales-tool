package org.yynn.resm.web.tag;

/**
 * <p>
 * Title: ReportQueryFormEntryTag
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-6-23
 */
public class ReportQueryFormEntryTag extends FormEntryTag {
  public static final String DEFAULTPRINTCODE = "common.print";

  public static final String DEFAULTPRINTFUNC = "printReport";

  private String printCode = DEFAULTPRINTCODE;

  private String printFunc = DEFAULTPRINTFUNC;

  /**
   * @return the printFunc
   */
  public String getPrintFunc() {
    return printFunc;
  }

  /**
   * @param printFunc
   *          the printFunc to set
   */
  public void setPrintFunc(String printFunc) {
    this.printFunc = printFunc;
  }

  /**
   * @return the printCode
   */
  public String getPrintCode() {
    return printCode;
  }

  /**
   * @param printCode
   *          the printCode to set
   */
  public void setPrintCode(String printCode) {
    this.printCode = printCode;
  }

  /*
   * @see org.yynn.resm.web.tag.FormEntryTag#generateOtherAction(java.lang.StringBuffer)
   */
  @Override
  protected void generateOtherAction(StringBuffer content) {
    content.append("&nbsp;&nbsp;<input type=button value=" + getMessage(printCode));
    content.append(" class=button onclick=");
    content.append(printFunc);
    content.append("(this.form)>");
  }

}
