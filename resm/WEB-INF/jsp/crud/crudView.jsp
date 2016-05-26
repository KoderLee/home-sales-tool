<%@ include file="../public/includes.jsp"%>
<html>
  <head>
    <link href=${pcss}/style.css rel=stylesheet type=text/css>
    <script language="JavaScript" src="${pjs}/Date.js"></script>
    <script language="javascript">
    function goForward(formId, actionPath) {
        with (document.getElementById(formId)) {
          action = actionPath;
          submit();
        }
    }
   function goDelete(formId, actionPath) {
        with (document.getElementById(formId)) {
          question = confirm('<s:message code="common.delete.confirm" />');
          if (question != '0') {
            action = actionPath;
            submit();
          }          
        }
    }
    </script>
  </head>
  <body ondragstart=return false>
    <table width=90% border=0 cellspacing=1 cellpadding=0 align=center class=table>
      <r:viewEntrySet modifyPath="${proot}${modifyPath}" deletePath="${proot}${deletePath}"
        returnPath="${proot}${returnPath}" titleCode="${titleCode}"  deleteAuthCode="${deleteAuthCode}" modifyAuthCode="${modifyAuthCode}" viewAuthCode="${viewAuthCode}"/>
    </table>
  </body>
</html>
