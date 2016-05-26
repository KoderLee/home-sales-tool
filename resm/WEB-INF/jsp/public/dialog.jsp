<%@ include file="/jsp/public/include/page.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE><bean:message key="aomis.common.project.name"/></TITLE>
<link rel="stylesheet" type="text/css" href="<%= css%>/style.css">
<head>
  <%
    String qs = request.getQueryString();
    int i = qs.indexOf("URL=");
    qs = qs.substring(i + 4);
    qs = request.getContextPath()+qs;
  %>
</head>
<BODY>
  <IFRAME name="iWindow" align="middle" width="100%" height="100%" src="<%=qs%>"></IFRAME>
</BODY>
</HTML>