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
	table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
	table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;} 
</style>
<section id="notice-container">
       <h2>공지사항</h2>
       <%-- <%if(loginMember != null && loginMember.getUserId().equals("admin")){ %> --%>
       <c:if test="${not empty sessionScope.loginMember and sessionScope.loginMember.userId eq 'admin'}">
	       <div style="text-align:right">
	       		<button onclick="location.replace('${path}/notice/writeNotice.do')">공지작성</button>
	       </div>
	   </c:if>
       <%-- <%} %> --%>
       <table id="tbl-notice">
           <tr>
               <th>번호</th>
               <th>제목</th>
               <th>작성자</th>
               <th>첨부파일</th>
               <th>작성일</th>
           </tr>
           <%-- <%if(list != null){ %> --%>
           <c:if test="${not empty notices}">
           		<%-- <%for(int i=0;i<list.size();i++){ %> --%>
           		<c:forEach var="notice" items="${notices}" varStatus="vs">
           			<tr>
           				<td><c:out value="${notice.noticeNo}"/></td>
           				<td><a href='${path}/notice/readNotice.do?noticeNo=${notice.noticeNo}'><c:out value="${notice.noticeTitle}"/></a></td>
           				<td><c:out value="${notice.member.userId}"/></td>
           				<td>
							<c:if test="${not empty notice.filePath}">
								<img src="${path}/images/file.png" width="20" height="20">
							</c:if>       
							<c:if test ="${empty notice.filePath}">
								첨부파일없음
							</c:if>   				
           				</td>
           				<td><fmt:formatDate value="${notice.noticeDate}" type="date" dateStyle="long"/></td>
           			</tr>
           			<%-- <tr>
           				<td><%=list.get(i).getNoticeNo() %></td>
           				<td><a href='<%=request.getContextPath()%>/notice/readNotice.do?noticeNo=<%=list.get(i).getNoticeNo()%>'><%=list.get(i).getNoticeTitle() %></a></td>
           				<td><%=list.get(i).getNoticeWriter() %></td>
           				<td>
							<%if(list.get(i).getFilePath() != null) {%>
								<img src="<%=request.getContextPath()%>/images/file.png" width="20" height="20">
							<%} else { %>
								첨부파일없음
							<%} %>							
						</td>
           				<td><%=list.get(i).getNoticeDate() %></td>
           			</tr> --%>
           		<%-- <%} %> --%>
           		</c:forEach>
           </c:if>
           <c:if test="${empty notices }">
           <%-- <%} else {%> --%>
           		<tr>결과가 없습니다..</tr>
           <%-- <%} %> --%>
           </c:if>
       </table>
       <div id="pageBar">
       		<%=request.getAttribute("pageBar") %>
       </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>