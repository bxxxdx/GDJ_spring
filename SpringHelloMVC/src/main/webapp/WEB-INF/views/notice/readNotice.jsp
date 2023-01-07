<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<style>
	section#notice-container{width:600px; margin:0 auto; text-align:center;}
	section#notice-container h2{margin:10px 0;}
	table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
	table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
</style>
	<section id="notice-container">
		<h2>공지사항</h2>
        <table id="tbl-notice">
	        <tr>
	            <th>제 목</th>
	            <%-- <td><%=n.getNoticeTitle() %></td> --%>
	            <td><c:out value="${notice.noticeTitle}"/></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <%-- <td><%=n.getNoticeWriter() %></td> --%>
	            <td><c:out value="${notice.member.userId}"/></td>
	        </tr>
	        <tr>
	            <th>첨부파일</th>
	            <td>
	            	<c:if test="${notice.filePath != null}">
	            		<img src="${path}/images/file.png" width="20" onclick="fn_fileDown('<c:out value="${notice.filePath}"/>');">&nbsp;<c:out value="${notice.filePath}"/>
	            	</c:if>
	            	<c:if test="${notice.filePath == null}">
	            		첨부파일없음
	            	</c:if>
	           		<%-- <%if(n.getFilePath() != null) {%>
	           			<img src="<%=request.getContextPath()%>/images/file.png" width="20" onclick="fn_fileDown('<%=n.getFilePath()%>');">&nbsp;<%=n.getFilePath() %>
	           		<%} else {%>
	           			첨부파일없음
	           		<%} %> --%>
	           		
	            </td>
	        </tr>
	        <tr>
	            <th>내 용</th>
	            <%-- <td><%=n.getNoticeContent() %></td> --%>
	            <td><c:out value="${notice.noticeContent}"/></td>
	        </tr>
	        <c:if test="${not empty sessionScope.loginMember and sessionScope.loginMember.userId eq 'admin'}">
		        <tr>
		            <th colspan="2">
		                <input type="button" value="수정하기" onclick="location.replace('${path}/notice/updateNotice.do?noticeNo=${notice.noticeNo}')">
		                <input type="button" value="삭제하기" onclick="fn_delete('<c:out value="${notice.noticeNo}"/>','<c:out value="${notice.filePath}"/>');">
		            </th>
		        </tr>
	        </c:if>
    	</table>
    </section>
<script>
	const fn_fileDown = (fileName) => {
		//다운로드 서비스 호출
		if(confirm("다운로드 받으시겠습니까?")){
			location.assign("${path}/notice/fileDown.do?fileName="+fileName);
		}
	}
	
	const fn_delete = (noticeNo, fileName) => {
		if(confirm("게시물을 정말 삭제하시겠습니까?")) {
			location.replace("${path}/notice/deleteNotice.do?noticeNo="+noticeNo+"&fileName="+fileName);			
		}
	}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

















