<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
   
<%-- <%
	Member m=(Member)request.getAttribute("member");
%>   --%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인하기</title>
<style>
	div#checkId-container{
		text-align: center;
		padding-top:50px;
	}
	span#duplicated{
		color : red;
		font-weight:bolder; 
	}
</style>
</head>
<body>
	<div id="checkId-container">
		<%-- <%if(m==null){ %> --%>
		<c:if test="${member==null}">
			<%-- [<span><%=request.getParameter("userId") %></span>]는 사용가능합니다. --%>	
			[<span><c:out value="${param.userId}"/></span>]는 사용가능합니다.
			<br><br>
			<button type="button" >닫기</button>
		</c:if>
		<c:if test="${member!=null}">
		<%-- <%} else{%> --%>
			<%-- [<span id="duplicated"><%=request.getParameter("userId") %></span>]는 사용중입니다. --%>
			[<span id="duplicated"><c:out value="${param.userId}"/></span>]는 사용중입니다.
			<br><br>
			<!-- 아이디 재입력창 구성 -->
			<form action="${path}/member/idDuplicate.do" method="get">
				<input type="text" name="userId" id="userId">
				<input type="submit" value="중복검사" >
			</form>
		<%-- <%} %> --%>
		</c:if>
	</div>
	<script>
		const btn=document.querySelector("button[type=button]");
		btn.addEventListener("click",e=>{
			console.log(opener.enrollMemberFrm.userId);
			console.log(opener.enrollMemberFrm.password);
			opener.document.querySelector("input#userId_").value="${param.userId}"; 
			opener.document.querySelector("input#password_").focus();
			close();
		});
	
	</script>
</body>
</html>