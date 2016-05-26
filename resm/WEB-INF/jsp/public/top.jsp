<%@ include file="./includes.jsp" %>
<%@ page import="org.yynn.resm.common.security.*"%>
<html>
<head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
.free_text {
  font-size: 9pt;
  font-family: "ËÎÌו";
  color: #044F8C;
}
A:link {
  color: #044F8C;
  text-decoration: none
}

A:visited {
  font-weight: normal;
  color: #044F8C;
  text-decoration: none
}

A:hover {
  color: #CC6600;
  text-decoration: underline
}
-->
</style></head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr height="76" valign="bottom">
    <td align=left width="10">&nbsp;</td>
    <td align="left width="982" background="${pimages}/top.png">
      <font class="free_text"><s:message code="common.welcom" arguments="<%=CurrentUserInfoProvider.getUserName() %>"/>&nbsp;&nbsp;<a href="${proot}/j_acegi_logout" target="_parent"><s:message code="common.exit"/></a></font>
    </td>
  </tr>
</table>
</body>
