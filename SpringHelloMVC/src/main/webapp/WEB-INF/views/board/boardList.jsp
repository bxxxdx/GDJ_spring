<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%-- <%
	List<Board> list = (List<Board>)request.getAttribute("boards");
%>
<%@ include file = "/views/common/header.jsp" %> --%>
<style>
	section#board-container{width:600px; margin:0 auto; text-align:center;}
	section#board-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
	div#pageBar span.cPage{color: #0066ff;}
</style>
<section id="board-container">
	<h2>게시판 </h2>
	<c:if test="${loginMember != null}">
     	<div style="text-align:right">
     		<button onclick="location.replace('${path}/board/writeBoard.do')">게시물작성</button>
    	</div>
    </c:if>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		<c:if test="${not empty boards}">
			<c:forEach var="board" items="${boards}" varStatus="vs">
				<tr>
					<td>${board.boardNo}</td>
					<td>
						<a href="${path}/board/readBoard.do?boardNo=${board.boardNo}">${board.boardTitle}</a>
					</td>
					<td>${board.member.userId}</td>
					<td><fmt:formatDate value="${board.boardDate}" type="date" dateStyle="long"/></td>
					<td>
						<c:if test="${board.boardOriginalFilename!=null}">
							<img src = "${path}/resources/images/file.png">
						</c:if>
						<c:if test="${board.boardOriginalFilename==null}">
							첨부파일없음
						</c:if>
						<td>${board.boardReadcount}</td>
					</td> 
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty boards}">
			<tr>
				<td colspan="6">조회된 데이터가 없습니다.</td>
			</tr>
		</c:if>
	</table>
	<div id="pageBar">
		${pageBar}
	</div>
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>