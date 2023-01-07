<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/> 
<%-- <%
	Board b = (Board)request.getAttribute("board");
%> --%>
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>
<section>
    <div id="board-container">
    <form action="${path}/board/updateBoardEnd.do" method="post">
        <table id="tbl-board">
	        <tr>
	            <th>제 목</th>
	            <td><input type="text" name="boardTitle" value="${board.boardTitle}" required></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><input type="text" name="boardWriter" value="${board.member.userId}" readonly></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td><input type="file" name="upfile" value="${board.boardRenamedFilename}"></td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <td><textarea name="boardContent" cols="40" rows="8">${board.boardContent}</textarea></td>
	        </tr>
	        <tr>
	            <th colspan="2">
	                <input type="submit" value="수정하기">
	            </th>
	        </tr>
    	</table>
	    <div>
	    	<input type="hidden" name="boardNo" value ="${board.boardNo}">
	    </div>
    </form>
    </div>    
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/> 