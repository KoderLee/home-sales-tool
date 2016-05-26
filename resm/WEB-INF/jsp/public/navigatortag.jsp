<%@ include file="./includes.jsp" %>
<HTML>
<HEAD>
<link href="<%= css%>/style.css" rel="stylesheet" type="text/css">

</HEAD>

<BODY bgcolor="#FFFFFF">
	<r:navigatorTree targetBean="navigatorTree" rootPath="<%=root%>" jsPath="<%=js%>" imagePath="<%=images%>" target="frame_right"/>
</BODY>
</HTML>