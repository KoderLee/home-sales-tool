<%@ include file="../public/includes.jsp"%>
<html>
  <head>
    <link href=${pcss}/style.css rel=stylesheet type=text/css>
    <style type="text/css">
    <!--
      .menu {padding-bottom: 5px}
      td {font-family: "ËÎÌו"; font-size: 9pt}
      body{margin-left:0px;	margin-top:0px; margin-bottom:0px; margin-right:0px;}
    -->
    </style>
    <script language="javascript">
      function vexchange(choice){
        var menu = document.all.tags("td");
        for (i=1; i< menu.length; i++) {         
          if(menu[i].className == "menu") {         
            if (i == choice+1) {            
              menu[i].setAttribute("background", "${pimages}/bar/bar1_11.jpg");
            }
            else {
              menu[i].setAttribute("background", "${pimages}/bar/bar1_1.jpg");
            }   
          }
        }
        var curl = "${proot}" + window.document.getElementById("uri_"+choice).value;     
        timer = setTimeout("parent.mbody.location='"+ curl +"'",0);
      }
    </script>
  </head>

  <body ondragstart=return false>
    <c:forEach items="${uriInfoList}" var="uriInfo" varStatus="status">
      <input type=hidden value=${uriInfo.uri} id=uri_${status.index} name=uri_${status.index}>
    </c:forEach>
    <form>
    <table width=100% border=0 cellpadding=0 cellspacing=0 height=31>
      <tr>
        <td width=50 background=${pimages}/bar/bar1bg.jpg>
          &nbsp;
        </td>
        <c:forEach items="${uriInfoList}" var="uriInfo" varStatus="status">
          <c:choose>
            <c:when test="${status.index == 0}">
              <td class=menu width=111 id=uri_${status.index} valign=bottom style=cursor:pointer 
                  onclick=vexchange(${status.index})  background=${pimages}/bar/bar1_11.jpg align=center>
                <s:message code="${uriInfo.msgCode}" />
              </td>
            </c:when>
            <c:otherwise>
              <td class=menu width=111 id=uri_${status.index} valign=bottom style=cursor:pointer 
                  onclick=vexchange(${status.index})  background=${pimages}/bar/bar1_1.jpg align=center>
                <s:message code="${uriInfo.msgCode}" />
              </td>
            </c:otherwise>
          </c:choose>          
        </c:forEach>
        <td background=${pimages}/bar/bar1bg.jpg>
          &nbsp;
        </td>
      </tr>
    </table>
    </form>
  </body>
</html>
