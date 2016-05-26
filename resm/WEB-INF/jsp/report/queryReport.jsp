<%@ include file="../public/includes.jsp"%>
<html>
  <head>
    <link href=${pcss}/style.css rel=stylesheet type=text/css>
    <script language="JavaScript" src="${pjs}/Date.js"></script>
    <script language=JavaScript>
    function printReport(queryForm) {      
      with (queryForm) {
        var spath = queryForm.action;
        var starget = queryForm.target;
        action = "${proot}${printPath}";
        target = "printView";
        window.open('waiting.htm','printView','top=184,left=200,width=600,height=400,toolbar=no,menubar=no,scrollbars=auto,resizable=yes,location=no,status=yes');
        submit();
        action = spath;
        target = starget;
      }
    }        
    </script>
  </head>
  <body ondragstart=return false>
    <table width=90% border=0 cellspacing=1 cellpadding=0 align=center class=table>
      <form name=query action=${proot}${actionPath} method=post target=mreport>
        <r:reportFormSet submitCode="${submitCode}" titleCode="${titleCode}" modelValueName="modelViewValue" />
      </form>
    </table>
    <iframe topmargin=0 leftmargin=0 name=mreport id=mreport src=blank.htm width=100% height=550 scrolling=auto frameborder=0 style="background-color: #f7f5f6;">
    </iframe>
  </body>
</html>
