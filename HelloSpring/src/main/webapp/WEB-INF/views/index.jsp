<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- 
	헤더파일 불러오기
	title값을 전달해서 출력해야함 -> MainPage가 출력
 -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>
 <section>
 	<h2>Hello Spring</h2>
 	<img src="${path}/resources/images/logo-spring.png" id="center-image" alt="스프링로고">
 	<button class="btn btn-outline-success" onclick="fn_selectMemberList();">전체회원출력</button>
 	<div id="memberContainer">
 	</div>
 	
 	<button onclick="jsonTest();">회원가입</button>
 	
 	<h1>jpa 테스트하기</h1>
 	<h3><a href="${path}/jpa/insert?userId=">jpa회원 저장하기</a></h3>
 		<form action="${path}/jpa/insert">
 			<input type="text" name="userId"/>
 			<input type="submit" value="가입"/>	
 		</form>
 	<h3><a href="${path}/jpa/members">전체 jpa회원 조회하기</a></h3>	
 	<h3><a href="${path}/jpa/member?no=1">jpa회원 조회하기</a></h3>
 	<h3><a href="${path}/jpa/update?no=1&age=20&height=190.5&intro=어휴졸려">jpa회원 수정</a></h3>
 	<h3><a href="${path}/jpa/delete?no=1">jpa회원 삭제하기</a></h3>
 </section>
 <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script>
	function jsonTest(){
		//비동기식통신을 할때 js가 기본 제공하는 함수이용하기.
		//fetch()함수를 제공함, 동기식 통신을 위해서 promise객체로 반환을 함.
		//fetch(url주소, 옵션(전송get or post)
		//.then(response=>return response.json());
		//.then(data=>{로직})
		fetch("${path}/member/ajax/insert",{
			method:"post", //전송방법
			headers:{ //요청헤더설정
				"Content-Type":"application/json"
			}, 
			body:JSON.stringify({ //전송데이터
				userId:"test22",
				password:"1234",
				userName:"test22"
			})
		})
		.then(response=>{
			console.log(response); 
			return response.json();
		})
		.then(data=>{ //success callback function
			console.log(data);
		});
	}

	const fn_selectMemberList = () => {
		$.ajax({
			url:"${path}/member/selectMemberList.do",
			success:data=>{
				console.log(data);
				const table = $("<table>");
				const header = $("<tr>");
				table.append(header);
				header.append("<th>아이디</th>").append("<th>이름</th>").append("<th>나이</th>").append("<th>성별</th>").append("<th>전화번호</th>").append("<th>이메일</th>")
				.append("<th>주소</th>").append("<th>취미</th>").append("<th>가입일</th>");
				data.forEach(e=>{
					let tr = $("<tr>");
					tr.append($("<td>").text(e.userId));
					tr.append($("<td>").text(e.userName));
					tr.append($("<td>").text(e.age));
					tr.append($("<td>").text(e.gender));
					tr.append($("<td>").text(e.phone));
					tr.append($("<td>").text(e.email));
					tr.append($("<td>").text(e.address));
					tr.append($("<td>").text(e.hobby.toString()));
					tr.append($("<td>").text(new Date(e.enrollDate)));
					table.append(tr);
				});
				$("#memberContainer").append(table);	
			}
		});
	}
</script>