<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="회원정보수정"/>
</jsp:include>

<style>
div#enroll-container{width:400px; margin:0 auto; text-align:center;}
div#enroll-container input, div#enroll-container select {margin-bottom:10px;}
</style>
	
	<div id="enroll-container">
		<form name="memberEnrollFrm" method="post" action="${path}/member/enrollMemberEnd.do">
			<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="userId" id="userId_" readonly value="${member.userId}">
			<input type="text" class="form-control" placeholder="이름" name="userName" id="userName" required value="${member.userName}">
			<input type="number" class="form-control" placeholder="나이" name="age" id="age" value="${member.age}">
			<input type="email" class="form-control" placeholder="이메일" name="email" id="email" required value="${member.email}">
			<input type="tel" class="form-control" placeholder="전화번호 (예:01012345678)" name="phone" id="phone" maxlength="11" required value="${member.phone}">
			<input type="text" class="form-control" placeholder="주소" name="address" id="address" value="${member.address}">
			<select class="form-control" name="gender" required>
				<option value="" >성별</option>
				<option value="M" ${fn:contains(member.gender,"M")?"selected":""}>남</option>
				<option value="F" ${fn:contains(member.gender,"F")?"selected":""}>여</option>
			</select>
			<div class="form-check-inline form-check">
				취미 : &nbsp; 
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby0" value="운동" ${fn:contains(fn:join(member.hobby,","),'운동')?"checked":""}><label for="hobby0" class="form-check-label">운동</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby1" value="등산" ${fn:contains(fn:join(member.hobby,","),'등산')?"checked":""}><label for="hobby1" class="form-check-label">등산</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby2" value="독서" ${fn:contains(fn:join(member.hobby,","),'독서')?"checked":""}><label for="hobby2" class="form-check-label">독서</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby3" value="게임" ${fn:contains(fn:join(member.hobby,","),'게임')?"checked":""}><label for="hobby3" class="form-check-label">게임</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby4" value="여행" ${fn:contains(fn:join(member.hobby,","),'여행')?"checked":""}><label for="hobby4" class="form-check-label">여행</label>&nbsp;
			</div>
			<br />
			<input type="submit" class="btn btn-outline-success" value="수정하기" >&nbsp;
			<input type="reset" class="btn btn-outline-danger" value="취소">
		</form>
	</div>

 <jsp:include page="/WEB-INF/views/common/footer.jsp"/>