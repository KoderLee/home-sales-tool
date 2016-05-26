package org.yynn.resm.web.tag;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.yynn.resm.common.security.CurrentUserInfoProvider;

/**
 * <p>
 * Title: AuthTreeNavigatorTag
 * </p>
 * <p>
 * Description: 树型导航标签,根据系统配置生成功能导航树,与应用根目录下以下文件协同使用: 1./js/navigator.js 2./images/tree/*.gif
 * </p>
 * 
 * @author ln.linan@gmail.com
 * @version 1.0
 * @created 2007-5-20
 */
public class AuthTreeNavigatorTag extends BaseTagSupport {
	public static final String JS_FILE_NAME = "navigator.js";

	public static final String COLLAPSEDLASTNODE_FILE_NAME = "collapsedLastNode.gif";

	public static final String EXPANDEDLASTNODE_FILE_NAME = "expandedLastNode.gif";

	public static final String OPENFOLDER_FILE_NAME = "openFolder.gif";

	public static final String OPENINFOLDER_FILE_NAME = "openinFolder.gif";

	public static final String BLANKSPACE_FILE_NAME = "blankSpace.gif";

	public static final String SPACER_FILE_NAME = "spacer.gif";

	public static final String NONFOLDER1_FILE_NAME = "nonFolder1.gif";

	public static final String NONFOLDER_FILE_NAME = "nonFolder.gif";

	public static final String N_FILE_NAME = "n.gif";

	public static final String DOWN_FILE_NAME = "down.gif";

	public static final String MINUS_FILE_NAME = "minus.gif";

	public static final String PLUS_FILE_NAME = "plus.gif";

	private String targetBean;

	private String imagePath;

	private String jsPath;

	private String rootPath;

	private String target;

	private String js_file_path;

	private String collapsedLastNode_file_path;

	private String expandedLastNode_file_path;

	private String openFolder_file_path;

	private String openinFolder_file_path;

	private String blankSpace_file_path;

	private String spacer_file_path;

	private String nonFolder1_file_path;

	private String nonFolder_file_path;

	private String n_file_path;

	private String down_file_path;

	private String minus_file_path;

	private String plus_file_path;

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
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @return the jsPath
	 */
	public String getJsPath() {
		return jsPath;
	}

	/**
	 * @param jsPath the jsPath to set
	 */
	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	/**
	 * @return the rootPath
	 */
	public String getRootPath() {
		return rootPath;
	}

	/**
	 * @param rootPath the rootPath to set
	 */
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	/**
	 * @return the targetBean
	 */
	public String getTargetBean() {
		return targetBean;
	}

	/**
	 * @param targetBean the targetBean to set
	 */
	public void setTargetBean(String targetBean) {
		this.targetBean = targetBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.yynn.resm.web.tag.BaseTagSupport#handleStartTag()
	 */
	protected int handleStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		WebApplicationContext wactx = RequestContextUtils.getWebApplicationContext(pageContext.getRequest());
		NavigatorTree tree = (NavigatorTree) wactx.getBean(targetBean);
		StringBuffer content = new StringBuffer("");
		try {
			preparePath();
			ArrayList<NavigatorTreeNode> rnodes = filterAuthTreeNode(tree.getNodes());
			init(content, rnodes);
			outTree(content, tree, rnodes);
			out.print(content.toString());
		}
		catch (IOException ex) {
			throw new JspException("Can't write with JspWriter", ex);
		}

		return SKIP_BODY;
	}

	/**
	 * 初始化所需文件路径
	 */
	private void preparePath() {
		String imagePathPrefix = imagePath + "/tree/";
		this.js_file_path = jsPath + "/" + JS_FILE_NAME;

		this.collapsedLastNode_file_path = imagePathPrefix + COLLAPSEDLASTNODE_FILE_NAME;
		this.expandedLastNode_file_path = imagePathPrefix + EXPANDEDLASTNODE_FILE_NAME;
		this.openFolder_file_path = imagePathPrefix + OPENFOLDER_FILE_NAME;
		this.openinFolder_file_path = imagePathPrefix + OPENINFOLDER_FILE_NAME;
		this.blankSpace_file_path = imagePathPrefix + BLANKSPACE_FILE_NAME;
		this.spacer_file_path = imagePathPrefix + SPACER_FILE_NAME;
		this.nonFolder1_file_path = imagePathPrefix + NONFOLDER1_FILE_NAME;
		this.nonFolder_file_path = imagePathPrefix + NONFOLDER_FILE_NAME;
		this.n_file_path = imagePathPrefix + N_FILE_NAME;
		this.down_file_path = imagePathPrefix + DOWN_FILE_NAME;
		this.minus_file_path = imagePathPrefix + MINUS_FILE_NAME;
		this.plus_file_path = imagePathPrefix + PLUS_FILE_NAME;
	}

	/**
	 * 初始化
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void init(StringBuffer content, ArrayList<NavigatorTreeNode> nodes) throws IOException {
		content.append("<script language='JavaScript' src='" + js_file_path + "'></script>");
		content.append("<script language='javascript'>");
		content.append("onload = doInit;");
		content.append("function doInit(){");
		content.append("  initIt();");
		for (int i = 0, n = nodes.size(); i < n; i++) {
			if (nodes.get(i).isExpand() && null != nodes.get(i).getChildren()) {
				content.append("  expandIt('KB" + (i + 1) + "');");
			}
		}
		content.append("}");
		content.append("</script>");
	}

	/**
	 * 输出导航树
	 * 
	 * @param out
	 * @throws IOException
	 */
	private void outTree(StringBuffer content, NavigatorTree tree, ArrayList<NavigatorTreeNode> nodes) throws IOException {
		content.append("<DIV>");
		content.append(" <TABLE cellSpacing=0 cellPadding=0 border=0>");
		content.append("  <TBODY>");
		content.append("   <TR>");
		content.append("    <TD class=color noWrap>" + getMessage(tree.getTitleCode()) + "</TD>");
		content.append("   </TR>");
		content.append("  </TBODY>");
		content.append(" </TABLE>");
		content.append("</DIV>");

		outBranchTreeNodes(content, nodes);
	}

	/**
	 * 过滤无权查看的功能节点
	 * 
	 * @param nodes
	 * @return
	 */
	protected ArrayList<NavigatorTreeNode> filterAuthTreeNode(ArrayList<NavigatorTreeNode> nodes) {
		ArrayList<NavigatorTreeNode> authNodes = new ArrayList<NavigatorTreeNode>();
		for (NavigatorTreeNode node : nodes) {
			if (node instanceof AuthNavigatorTreeNode) {
				if (CurrentUserInfoProvider.hasAuth(((AuthNavigatorTreeNode) node).getRequiredCodes())) {
					authNodes.add(node);
				}
			}
			else
				authNodes.add(node);
		}

		return authNodes;
	}

	/**
	 * 输出枝节点
	 * 
	 * @param out
	 * @param nodes
	 * @throws IOException
	 */
	private void outBranchTreeNodes(StringBuffer content, ArrayList<NavigatorTreeNode> nodes) throws IOException {
		if (null == nodes || 0 == nodes.size()) return;
		for (int i = 0, n = nodes.size(); i < n; i++) {
			boolean isEnd = i == n - 1;
			boolean hasChildren = null != nodes.get(i).getChildren();
			String cm_path = isEnd ? collapsedLastNode_file_path : plus_file_path;
			String ep_path = isEnd ? expandedLastNode_file_path : minus_file_path;

			if (!hasChildren) cm_path = ep_path;

			String nid = "KB" + (i + 1);
			content.append("<DIV class=parent id=" + nid + "Parent noWrap>");
			content.append(" <TABLE cellSpacing=0 cellPadding=0 border=0>");
			content.append("  <TBODY>");
			content.append("   <TR>");
			if (hasChildren) content.append("    <A onClick=\"expandIt('" + nid + "'); return false\" href='#'>");
			content.append("    <TD noWrap>");
			content.append("<IMG id=" + nid + "P src='" + cm_path + "' width=16 height=22 border=0>");
			content.append("<IMG id=" + nid + "O src='" + ep_path + "' width=16 height=22 border=0>");
			content.append("<IMG id=" + nid + "Q src='" + openFolder_file_path + "' width=16 height=22 border=0>");
			content.append("<IMG id=" + nid + "L src='" + openinFolder_file_path + "' width=16 height=22 border=0>");
			content.append("</TD>");
			content.append("    <TD class=color noWrap>" + getMessage(nodes.get(i).getDisplayCode()) + "</TD>");
			if (hasChildren) content.append("    </A>");
			content.append("   </TR>");
			content.append("  </TBODY>");
			content.append("</TABLE>");
			content.append("</DIV>");

			outLeafTreeNodes(nid, content, filterAuthTreeNode(nodes.get(i).getChildren()), isEnd);
		}

	}

	/**
	 * 输出枝节点
	 * 
	 * @param out
	 * @param nodes
	 * @throws IOException
	 */
	private void outLeafTreeNodes(String parentId, StringBuffer content, ArrayList<NavigatorTreeNode> nodes, boolean isEnd)
			throws IOException {
		if (null == nodes || 0 == nodes.size()) return;

		String spacerPath = isEnd ? blankSpace_file_path : spacer_file_path;
		content.append("<DIV class=child id=" + parentId + "Child noWrap>");
		content.append(" <TABLE cellSpacing=0 cellPadding=0 border=0>");
		content.append("  <TBODY>");
		for (int i = 0, n = nodes.size(); i < n; i++) {
			String ndownPath = i == n - 1 ? down_file_path : n_file_path;
			String href = rootPath + nodes.get(i).getHref();
			String nodeTarget = nodes.get(i).getTarget();
			content.append("   <TR>");
			content.append("    <TD noWrap>");
			content.append("<IMG src='" + spacerPath + "' border=0 height=22>");
			content.append("<IMG src='" + ndownPath + "' border=0 height=22>");
			content.append("</TD>");
			content.append("    <TD noWrap>");
			content.append("    <A class=link href=" + href + " target=" + (nodeTarget == null ? target : nodeTarget)
					+ " onMouseOut=MM_swapImgRestore() onMouseOver=MM_swapImage('Image+" + parentId + i + "','','"
					+ nonFolder1_file_path + "',1)>");
			content.append("    <img src=" + nonFolder_file_path + " border=0 name='Image+" + parentId + i
					+ "' onMouseOut=MM_swapImgRestore() onMouseOver=MM_swapImage('Image+" + parentId + i + "','','"
					+ nonFolder1_file_path + "',1) height=14>");
			content.append(getMessage(nodes.get(i).getDisplayCode()));
			content.append("    </A>");
			content.append("   </TD>");
			content.append("  </TR>");
		}
		content.append("  </TBODY>");
		content.append(" </TABLE>");
		content.append("</DIV>");
	}

}
