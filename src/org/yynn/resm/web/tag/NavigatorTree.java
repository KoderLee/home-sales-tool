package org.yynn.resm.web.tag;

import java.util.ArrayList;

/**
 * <p>
 * Title: NavigatorTree
 * </p>
 * <p>
 * Description: ���͵���������,��TreeNavigatorTagʹ��.
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class NavigatorTree {
  private String titleCode;
  
  private ArrayList<NavigatorTreeNode> nodes;

  /**
   * @return the nodes
   */
  public ArrayList<NavigatorTreeNode> getNodes() {
    return nodes;
  }

  /**
   * @param nodes the nodes to set
   */
  public void setNodes(ArrayList<NavigatorTreeNode> nodes) {
    this.nodes = nodes;
  }

  /**
   * @return the titleCode
   */
  public String getTitleCode() {
    return titleCode;
  }

  /**
   * @param titleCode the titleCode to set
   */
  public void setTitleCode(String titleCode) {
    this.titleCode = titleCode;
  }
}
