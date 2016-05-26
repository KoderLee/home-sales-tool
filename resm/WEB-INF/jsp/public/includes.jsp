<%@ page session="false"%>
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="s" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="r" uri="/WEB-INF/resm.tld"%>
<%
      response.addHeader("Cache-Control", "no-cache");
      response.addHeader("Expires", "Thu, 01 Jan 1970 00:00:01 GMT");

      String root = request.getContextPath();
      String images = root + "/images";
      String css = root + "/css";
      String js = root + "/js";
%>

<c:set value="<%=root %>" var="proot" scope="page" />
<c:set value="<%=images %>" var="pimages" scope="page" />
<c:set value="<%=css %>" var="pcss" scope="page" />
<c:set value="<%=js %>" var="pjs" scope="page" />