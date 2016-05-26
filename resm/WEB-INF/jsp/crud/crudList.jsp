<%@ include file="../public/includes.jsp"%>
<html>
  <head>
    <link href=${pcss}/style.css rel=stylesheet type=text/css>
    <script language="JavaScript" src="${pjs}/Date.js"></script>
    <script language=JavaScript>
    function deleteConfirm(formId, delURL) {
      with (document.getElementById(formId)) {
        question = confirm('<s:message code="common.delete.confirm" />');
        if (question != '0') {
          action = delURL;
          submit();
        }
      }
    }
    function view(formId, actionPath) {
        with (document.getElementById(formId)) {
          action = actionPath;
          submit();
        }
    }
    </script>
  </head>
  <body ondragstart=return false>
    <table width=90% border=0 cellspacing=1 cellpadding=0 align=center class=table>
      <form name=query action=${proot}${actionPath} method=post>
        <r:formEntrySet submitCode="${submitCode}" titleCode="${titleCode}" modelValueName="modelViewValue" />
      </form>
    </table>
    <br>
    <c:if test="${not empty requestScope['PROCESS_RESULT_KEY']}">
      <table width=90% border=0 cellspacing=0 cellpadding=0 align=center class=blanktable>
        <tr>
          <td class=result_info>
            <s:message code="${requestScope['PROCESS_RESULT_KEY']}" />
            </font>
          </td>
        </tr>
      </table>
    </c:if>
    <table width=90% border=0 cellspacing=1 cellpadding=0 align=center class=table>
      <r:queryList viewPath="${proot}${viewPath}" deletePath="${proot}${deletePath}" deleteAuthCode="${deleteAuthCode}" modifyAuthCode="${modifyAuthCode}" viewAuthCode="${viewAuthCode}"/>
    </table>
  </body>
</html>
