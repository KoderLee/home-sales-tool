<%@ include file="./includes.jsp" %>
<HTML>
<HEAD>
<link href="<%= css%>/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="${pjs}/navigator.js"></script>

</HEAD>

<BODY bgcolor="#FFFFFF">
<script language="javascript">
	onload = initIt;
</script>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD class=color noWrap><s:message code="menu.title"/></TD>
  </TR>
  </TBODY>
</TABLE>
</DIV>

<% String id="KB1";%>

<DIV class=parent id=<%=id%>Parent noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <A onClick="expandIt('<%=id%>'); return false" href="#">
    <TD noWrap>
      <IMG id=<%=id%>P src="<%= images%>/tree/collapsedLastNode.gif" width=16 height=22 border=0><IMG
      id=<%=id%>O src="<%= images%>/tree/expandedLastNode.gif" width=16 height=22 border=0><img
      id=<%=id%>Q src="<%= images%>/tree/openFolder.gif" width=24 height=22 border=0><img
      id=<%=id%>L src="<%= images%>/tree/openinFolder.gif" width=24 height=22 border=0></TD>
    <TD class=color noWrap><s:message code="menu.company.workspace"/></TD>
    </A>
  </TR>
  </TBODY>
</TABLE>

</DIV>

<DIV class=child id=<%=id%>Child noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/spacer.gif" border=0 height=22><IMG
      src="<%= images%>/tree/n.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/manage/passwordAction.do?method=toPassword" target=frame_right onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image51" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)" height=14>6666666666666</A>
    </TD>
  </TR>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/spacer.gif" border=0 height=22><IMG
      src="<%= images%>/tree/down.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/jsp/public/logoff.jsp" target=_parent onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image53" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)" height=14>555555555555</A>
    </TD>
  </TR> 
  </TBODY>
</TABLE>
</DIV>

<% id="KB2";%>

<DIV class=parent id=<%=id%>Parent noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <A onClick="expandIt('<%=id%>'); return false" href="#">
    <TD noWrap>
      <IMG id=<%=id%>P src="<%= images%>/tree/collapsedLastNode.gif" width=16 height=22 border=0><IMG
      id=<%=id%>O src="<%= images%>/tree/expandedLastNode.gif" width=16 height=22 border=0><img
      id=<%=id%>Q src="<%= images%>/tree/openFolder.gif" width=24 height=22 border=0><img
      id=<%=id%>L src="<%= images%>/tree/openinFolder.gif" width=24 height=22 border=0></TD>
    <TD class=color noWrap>3333333333333</TD>
    </A>
  </TR>
  </TBODY>
</TABLE>

</DIV>

<DIV class=child id=<%=id%>Child noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/spacer.gif" border=0 height=22><IMG
      src="<%= images%>/tree/n.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/manage/passwordAction.do?method=toPassword" target=frame_right onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image51" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)" height=14>6666666666666</A>
    </TD>
  </TR>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/spacer.gif" border=0 height=22><IMG
      src="<%= images%>/tree/down.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/jsp/public/logoff.jsp" target=_parent onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image53" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)" height=14>555555555555</A>
    </TD>
  </TR> 
  </TBODY>
</TABLE>
</DIV>

<% id="KB3";%>

<DIV class=parent id=<%=id%>Parent noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <A onClick="expandIt('<%=id%>'); return false" href="#">
    <TD noWrap>
      <IMG id=<%=id%>P src="<%= images%>/tree/collapsedLastNode.gif" width=16 height=22 border=0><IMG
      id=<%=id%>O src="<%= images%>/tree/expandedLastNode.gif" width=16 height=22 border=0><img
      id=<%=id%>Q src="<%= images%>/tree/openFolder.gif" width=24 height=22 border=0><img
      id=<%=id%>L src="<%= images%>/tree/openinFolder.gif" width=24 height=22 border=0></TD>
    <TD class=color noWrap>3333333333333</TD>
    </A>
  </TR>
  </TBODY>
</TABLE>

</DIV>

<DIV class=child id=<%=id%>Child noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/spacer.gif" border=0 height=22><IMG
      src="<%= images%>/tree/n.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/manage/passwordAction.do?method=toPassword" target=frame_right onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image51" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)" height=14>6666666666666</A>
    </TD>
  </TR>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/spacer.gif" border=0 height=22><IMG
      src="<%= images%>/tree/down.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/jsp/public/logoff.jsp" target=_parent onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image53" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)" height=14>555555555555</A>
    </TD>
  </TR> 
  </TBODY>
</TABLE>
</DIV>

<% id="KB4";%>

<DIV class=parent id=<%=id%>Parent noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <A onClick="expandIt('<%=id%>'); return false" href="#">
    <TD noWrap>
      <IMG id=<%=id%>P src="<%= images%>/tree/collapsedLastNode.gif" width=16 height=22 border=0><IMG
      id=<%=id%>O src="<%= images%>/tree/expandedLastNode.gif" width=16 height=22 border=0><img
      id=<%=id%>Q src="<%= images%>/tree/openFolder.gif" width=24 height=22 border=0><img
      id=<%=id%>L src="<%= images%>/tree/openinFolder.gif" width=24 height=22 border=0></TD>
    <TD class=color noWrap>3333333333333</TD>
    </A>
  </TR>
  </TBODY>
</TABLE>

</DIV>

<DIV class=child id=<%=id%>Child noWrap>

<TABLE cellSpacing=0 cellPadding=0 border=0>
  <TBODY>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/blankSpace.gif" border=0 height=22><IMG
      src="<%= images%>/tree/n.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/manage/passwordAction.do?method=toPassword" target=frame_right onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image51" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image51','','<%= images%>/tree/nonFolder1.gif',1)" height=14>6666666666666</A>
    </TD>
  </TR>
  <TR>
    <TD noWrap>
      <IMG alt="" src="<%= images%>/tree/blankSpace.gif" border=0 height=22><IMG
      src="<%= images%>/tree/down.gif" border=0 height=22></TD>
    <TD noWrap>
      <A class=link href="<%= root%>/jsp/public/logoff.jsp" target=_parent onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)">
      <img src="<%= images%>/tree/nonFolder.gif" border="0" name="Image53" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image53','','<%= images%>/tree/nonFolder1.gif',1)" height=14>555555555555</A>
    </TD>
  </TR> 
  </TBODY>
</TABLE>
</DIV>

</BODY>
</HTML>