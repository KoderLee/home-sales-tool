<%@ include file="../public/include/page.jsp" %>
<html:html>
<head>
<link rel="stylesheet" type="text/css" href="<%= css%>/style.css">
<script language="Javascript">
function document.oncontextmenu(){event.returnValue= false;}
</script>
</head>
<body>
<br/>
<br/>
<table width="50%" height="60" border="0" cellspacing="1" cellpadding="0" align="center" class="table">
	<tr>
		<td class="title">&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td class="text1">	
		<br/>	
		
		<font class="result_info">
		<bean:message key="errors.header"/>
        <html:messages id="messages" message="true"> 
        <bean:write name="messages"/> 
        </html:messages> 
        <bean:message key="errors.footer"/>
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