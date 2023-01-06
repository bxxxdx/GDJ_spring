<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<%-- <%
	String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
	String script = (String)request.getAttribute("script");
%> --%>

<html>
<head>
<meta charset="UTF-8">
<title>메세지 페이지</title>
</head>
<body>
	<script>
		<%-- alert('<%=msg%>'); --%>
		alert('${msg}');
		<%-- location.replace('<%=request.getContextPath()%><%=loc%>'); --%>
		location.replace('${path}${loc}');
		<%-- <%=script!=null?script:""%> --%>
		${script}
	</script>
</body>
</html>
