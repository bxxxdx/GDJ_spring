<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="com.web.common.AESEncrypt" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section id="content">
	
	<h2 align="center" style="margin-top:200px;">안녕하세요, MVC입니다. ${loginMember}</h2>
	
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		
		
		
		
		
		
