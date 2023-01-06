<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에러발생</h1>
	<h3 style="color:red;"><%=exception.getMessage()%></h3>
	 <h3>${exception }</h3>
	<%-- <c:catch var="ex">
	</c:catch>
	<c:if test="${exception != null}">
		${ex}
		gg
	</c:if> --%>
	<script>
		setTimeout(()=>{
			location.replace("${path}");
		},5000)
	</script>
</body>
</html>