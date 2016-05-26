<%@ include file="./includes.jsp"%>
<%@ page pageEncoding="GBK"%>
<html>
  <head>
    <title><s:message code="project.name" /></title>
    <meta http-equiv=Content-Type content="text/html;charset=gb2312">
    <style type="text/css">
	<!--
	body {  font-family: "宋体"; font-size: 9pt; color: #005193}
	td {  font-family: "宋体"; font-size: 9pt; color: #005193}
	-->
	</style>
    <script type="text/javascript">
      function clearAll() {
        window.document.getElementById("j_username").value="";
        window.document.getElementById("j_password").value="";
      }
    </script>
  </head>

  <body bgColor=#f7f5f6 onload=document.getElementById("j_username").focus()>
    <br>
    <br>
    <br>
    <br>
    <table border=0 cellPadding=0 cellSpacing=0 width=494 align="center">
      <tr>
        <td><IMG border=0 height=1 src="${pimages}/spacer.gif" width=120></td>
        <td><IMG border=0 height=1 src="${pimages}/spacer.gif" width=325></td>
        <td><IMG border=0 height=1 src="${pimages}/spacer.gif" width=49></td>
        <td><IMG border=0 height=1 src="${pimages}/spacer.gif" width=1></td>
      </tr>
      <tr>
        <td colSpan=3><IMG border=0 height=43 src="${pimages}/login_r1_c1.png" width=494></td>
        <td><IMG border=0 height=43 src="${pimages}/spacer.gif" width=1></td>
      </tr>
      <tr>
        <td><IMG border=0 height=159 src="${pimages}/login_r2_c1.jpg" width=120></td>
        <td bgcolor="#FFFFFF">
        <c:choose>
            <c:when test="${'1' == param.errorCode}">
              <pre>       <font color=blue><s:message code="errors.common.invalid.userinfo" /></font></pre>
            </c:when>
          </c:choose>
          <div align="center">
            <form action="${proot}/j_acegi_login" method="POST">
              <table width="75%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><div align="left"><s:message code="form.username" /></div><td>
                    <div align="left"><input onfocus="this.select()" style="border:1 solid #6699cc" type='text' name='j_username' size="18"></div>
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>
                    <div align="left"><s:message code="form.password" /></div>
                  </td>
                  <td>
                    <div align="left">
                      <input onfocus="this.select()" style="border:1 solid #6699cc" type='password' name='j_password' size="18">
                    </div>
                  </td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td>
                    <div align="left">
                      <input type='image' name='login' src="${pimages}/login.jpg">
                      <img style="cursor:pointer" onclick="clearAll()" src="${pimages}/quit.jpg" width="62" height="17" />
                    </div>
                  </td>
                </tr>
              </table>
            </form>
          </div>
        </td>
        <td><IMG border=0 height=159 src="${pimages}/login_r2_c3.jpg" width=49></td>
        <td><IMG border=0 height=159 src="${pimages}/spacer.gif" width=1></td>
      </tr>
      <tr>
        <td colSpan=3><IMG border=0 height=64 src="${pimages}/login_r3_c1.jpg" width=494></td>
        <td><IMG border=0 height=64 src="${pimages}/spacer.gif" width=1></td>
      </tr>
      <tr>
        <td colSpan=3><IMG border=0 height=47 src="${pimages}/login_r4_c1.png" width=494></td>
        <td><IMG border=0 height=47 src="${pimages}/spacer.gif" width=1></td>
      </tr>
    </table>
  </body>
</html>
