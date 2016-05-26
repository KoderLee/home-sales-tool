<%@ include file="./public/includes.jsp" %>

<html>
<head>
<title><s:message code="project.name"/></title>
</head>
<frameset rows="75,*" cols="*" framespacing="1" frameborder="yes"
    border="1" bordercolor="#000000">
    <frame src="${proot}/public/top.page" name="topFrame" frameborder="0" scrolling="no" noresize>
    <frameset cols="203,*" framespacing="7" frameborder="yes" border="1" bordercolor="#044F8C">
      <frame src="${proot}/public/navigatortag.page" name="leftFrame" frameborder="0" scrolling="auto">
      <frame src="${proot}/public/right.page" name="frame_right" frameborder="0" scrolling="auto">
    </frameset>
    <noframes>
      <body>
        <font class="result_info"><s:message code="common.frame.not.support"/></font>
      </body>
    </noframes>
</frameset>
</html>
