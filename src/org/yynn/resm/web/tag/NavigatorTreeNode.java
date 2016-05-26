package org.yynn.resm.web.tag;

import java.util.ArrayList;

/**
 * <p>
 * Title: NavigatorTreeNode
 * </p>
 * <p>
 * Description: 树型导航节点对象
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class NavigatorTreeNode {
  private String displayCode;
  
  private String href;
  
  private ArrayList<NavigatorTreeNode> children;
  
  private boolean isExpand = false;
  
  private String target;

  /**
   * @return the target
   */
  public String getTarget() {
    return target;
  }

  /**
   * @param target the target to set
   */
  public void setTarget(String target) {
    this.target = target;
  }

  /**
   * @return the children
   */
  public ArrayList<NavigatorTreeNode> getChildren() {
    return children;
  }

  /**
   * @param children the children to set
   */
  public void setChildren(ArrayList<NavigatorTreeNode> children) {
    this.children = children;
  }

  /**
   * @return the displayCode
   */
  public String getDisplayCode() {
    return displayCode;
  }

  /**
   * @param displayCode the displayCode to set
   */
  public void setDisplayCode(String displayCode) {
    this.displayCode = displayCode;
  }

  /**
   * @return the href
   */
  public String getHref() {
    return href;
  }

  /**
   * @param href the href to set
   */
  public void setHref(String href) {
    this.href = href;
  }

  /**
   * @return the isExpand
   */
  public boolean isExpand() {
    return isExpand;
  }

  /**
   * @param isExpand the isExpand to set
   */
  public void setExpand(boolean isExpand) {
    this.isExpand = isExpand;
  }
}
