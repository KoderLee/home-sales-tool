<%@ include file="../public/include/page.jsp" %>
<%@ page pageEncoding="GBK"%>
<html:html>
<head>
<link rel="stylesheet" type="text/css" href="<%= css%>/style.css">
</head>
<body>
<br/>
<br/>
<table width="50%" height="60" border="0" cellspacing="1" cellpadding="0" align="center" class="table">
	<tr>
		<td class="title" align="left"><bean:message key="errors.common.title"/></td>
	</tr>
	<tr>
		<td class="text1">	
		<br/>	
		<font class="result_info">
       		<bean:message key="error.common.syserror"/>
        </font>	
        <br/>
        <br/>	
        </td>
	</tr>
	<tr>
		<td class="title">&nbsp;&nbsp;</td>      
	</tr>
</table>
</body>
</html:html>