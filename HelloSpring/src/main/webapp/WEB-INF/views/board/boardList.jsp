<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="게시글목록"/>
</jsp:include>

<section id="board-container" class="container">
		<div style="display:flex;justify-content:space-between;align-items: center;">
        	<p>총 ${totalContents }건의 게시물이 있습니다.</p>
            <button class="btn btn-outline-success" onclick="location.assign('${path}/board/boardWrite.do')">글쓰기</button>
        </div>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards}">
            	<c:forEach var="board" items="${boards}">
            		<tr>
            			<td><c:out value="${board.boardNo}"/></td>
            			<td>
            				<a href="${path}/board/boardView.do?boardNo=${board.boardNo}">${board.boardTitle}</a>
            			</td>
            			<td><c:out value="${board.boardWriter.userId}"/></td>
            			<td><fmt:formatDate value="${board.boardDate}" type="date" dateStyle="full"/></td>
            			<td>
            				<c:if test="${not empty board.files}">
            					<img src="${path}/resources/images/file.png"> 
            				</c:if>
            				<c:if test="${empty board.files}">
            					첨부파일없음
            				</c:if>
            			</td>
            			<td><c:out value="${board.boardReadcount}"/></td>
            		</tr>
            	</c:forEach>
            </c:if>
            <c:if test="${empty boards}">
            	<tr>
            		<td colspan="6">조회된 게시물이 없습니다.</td>
            	</tr>
            </c:if>
        </table>
        <div id="pageBar">
        	${pageBar}
        </div> 
</section>

 <jsp:include page="/WEB-INF/views/common/footer.jsp"/>