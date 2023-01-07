<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMVC Spring.Ver</title>
<script src="${path}/resources/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>HelloMVC Spring.Ver</h1>
			<div class="login-container">
				<%-- <%if(loginMember == null) {%> --%>
				<c:if test="${empty loginMember}">
					<form id="loginFrm" action="${path}/login.do" method="post">
						<table>
							<tr>
								<td>
									<input type="text" name="userId" id="userId" placeholder="아이디" value='${cookie.saveId.value}'>
								</td>
							</tr>
							<tr>
								<td>
									<input type="password" name="password" id="password" placeholder="패스워드">
								</td>						
								<td>
									<input type="submit" value="로그인">
								</td>
							</tr>
							<tr>
								<td>
									<%-- <input type="checkbox" name="saveId" id="saveId" <%=saveId!=null?"checked":"" %>> --%>
									<input type="checkbox" name="saveId" id="saveId" ${not empty cookie.saveId?"checked":""}>
									<label for="saveId">아이디저장</label>
									<input type="button" value="회원가입" onclick="location.replace('${path}/member/enrollMember.do')">
								</td>
							</tr>			
						</table>
					</form>
				</c:if>
				<%-- <%} else {%> --%>
				<c:if test="${not empty loginMember}">
					<table id="logged-in">
						<tr>
							<td colspan="2">
								<c:out value="${loginMember.userName}"/>님, 어서오고~
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" value="내 정보보기" onclick="location.assign('${path}/member/memberView.do?id=${sessionScope.loginMember.userId}')">
							</td> 
							<td>
								<%--<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/logout.do'"> --%>	
								<!-- 둘다 작동하긴 하는데 replce는 history를 안남겨서 더 적절하다. -->
								<input type="button" value="로그아웃" onclick="location.replace('${path}/logout.do')">
							</td>
						</tr>
					</table>
				<%-- <%} %> --%>
				</c:if>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="">Home</a></li>
					<li id="notice"><a href="${path}/notice/noticeList.do">공지사항</a></li>
					<li id="board"><a href="${path}/board/boardList.do">게시판</a></li>
					<li id="gallery"><a href="">갤러리</a></li>
					<%-- <%if(loginMember != null && loginMember.getUserId().equals("admin")){ %> --%>
					<c:if test="${not empty sessionScope.loginMember and sessionScope.loginMember.userId eq 'admin'}">
					<li id="memberManage">
						<a href="${path}/admin/memberList.do">회원관리</a>
					</li>
					</c:if>
					<%-- <%} %>  --%>
				</ul>
			</nav>
		</header>