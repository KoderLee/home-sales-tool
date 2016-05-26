<%@ include file="../public/includes.jsp"%>
<html>
  <head>
    <link href=${pcss}/style.css rel=stylesheet type=text/css>
    <script language="JavaScript" src="${pjs}/Date.js"></script>
  </head>
  <body ondragstart=return false>
    <c:choose>
      <c:when test="${not empty requestScope['ADD_PROCESS_FAIL_KEY']}">
        <table width=90% border=0 cellspacing=0 cellpadding=0 align=center class=blanktable>
          <tr>
            <td class=result_info>
              <s:message code="${requestScope['ADD_PROCESS_FAIL_KEY']}" />
  
            </td>
          </tr>
        </table>
      </c:when>  
      <c:when test="${not empty requestScope['PROCESS_RESULT_KEY']}">
        <table width=90% border=0 cellspacing=0 cellpadding=0 align=center class=blanktable>
          <tr>
            <td class=result_info>
              <s:message code="${requestScope['PROCESS_RESULT_KEY']}" />  
            </td>
          </tr>
        </table>
      </c:when>  
      <c:otherwise></c:otherwise>
    </c:choose>
    <table width=90% border=0 cellspacing=1 cellpadding=0 align=center class=table>
      <form name=query action=${proot}${actionPath} method=post>
        <r:formEntrySet titleCode="${titleCode}" modelValueName="modelViewValue" />
      </form>
    </table>
  </body>
</html>
