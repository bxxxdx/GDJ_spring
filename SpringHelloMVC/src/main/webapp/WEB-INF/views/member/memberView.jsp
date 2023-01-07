<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%-- <%
	Member m = (Member)request.getAttribute("member");
	String[] checkData = new String[5];
	if(m != null){
		for(String h : m.getHobby()){
			switch(h){
			case "운동" : checkData[0] ="checked";break;
			case "등산" : checkData[1] ="checked";break;
			case "독서" : checkData[2] ="checked";break;
			case "게임" : checkData[3] ="checked";break;
			case "여행" : checkData[4] ="checked";break;
			}
		}
	}
	
%> --%>

	<section id=enroll-container>
		<h2>회원 정보 수정</h2>
		<form id="memberFrm" method="post" >
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="userId" id="userId_" value="${member.userId}" readonly>
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>	
					<input type="text"  name="userName" id="userName" required value="${member.userName}"><br>
					</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>	
					<input type="number" name="age" id="age" value="${member.age}"><br>
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="${member.email}"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="${member.phone}"><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>	
						<input type="text" placeholder="" name="address" id="address" value="${member.address}"><br>
					</td>
				</tr>
				<tr>
					<th>성별 </th>
					<td>
							<input type="radio" name="gender" id="gender0" value="M" ${fn:contains(member.gender,"M")?"checked":""}>
							<label for="gender0">남</label>
							<input type="radio" name="gender" id="gender1" value="F" ${fn:contains(member.gender,"F")?"checked":""}>
							<label for="gender1">여</label>
						
						
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동" ${fn:contains(fn:join(member.hobby,","), "운동")?"checked":""}><label for="hobby0">운동</label>
						<input type="checkbox" name="hobby" id="hobby1" value="등산" ${fn:contains(fn:join(member.hobby,","), "등산")?"checked":""}><label for="hobby1">등산</label>
						<input type="checkbox" name="hobby" id="hobby2" value="독서" ${fn:contains(fn:join(member.hobby,","), "독서")?"checked":""}><label for="hobby2">독서</label><br/>
						<input type="checkbox" name="hobby" id="hobby3" value="게임" ${fn:contains(fn:join(member.hobby,","), "게임")?"checked":""}><label for="hobby3">게임</label>
						<input type="checkbox" name="hobby" id="hobby4" value="여행" ${fn:contains(fn:join(member.hobby,","), "여행")?"checked":""}><label for="hobby4">여행</label><br/>
					</td>
				</tr>
			</table>
			<input type="button" value="비밀번호변경" onclick="fn_updatePassword();"/>
			<input type="button" value="정보수정" onclick="fn_updateMember();"/>
			<input type="button" value="탈퇴" onclick="fn_deleteMember();"/>
		</form>
		<%-- <form action="" style="">
			<table>
				<tr>
					<th>패스워드</th>
					<td>
						<input type="password" name="password" id="password_" value="<%=m.getPassword()%>">
					</td>
				</tr>
				<tr>
					<th>패스워드확인</th>
					<td>	
						<input type="password" id="password_2"><br>
					</td>
				</tr>  
			</table>
		</form> --%>
	</section>
	<script>
		const fn_updatePassword = () => {
			//새창으로 패스워드 수정 페이지 연결
			<%-- window.open("<%=request.getContextPath()%>/member/updatePassword.do?userId=<%=loginMember!=null?loginMember.getUserId():""%>","_blank","width=400,height=220,left=500,top=200"); --%>
			window.open("${path}/member/updatePassword.do?userId=${loginMember!=null?loginMember.userId:''}","_blank","width=400,height=220,left=500,top=200");
		}
		const fn_updateMember = () => {
			$("#memberFrm").attr("action","${path}/member/updateMember.do");
			$("#memberFrm").submit();
		}
		const fn_deleteMember = () => {
			$("#memberFrm").attr("action","${path}/member/deleteMember.do");
			$("#memberFrm").submit();
		}
	
	</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>