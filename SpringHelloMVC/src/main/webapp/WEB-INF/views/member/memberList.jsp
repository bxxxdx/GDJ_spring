<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="com.web.member.model.vo.Member, java.util.List" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%-- <%
	List<Member> list = (List<Member>)request.getAttribute("members");
%> --%>
<style type="text/css">
	div#search-container {margin:0 0 10px 0; padding:3px; 
    background-color: rgba(0, 188, 212, 0.3);}
    div#search-userId{display:inline-block;}
    div#search-userName{display:none;}
    div#search-gender{display:none;}
    div#numPerpage-container{float:right;}
    form#numperPageFrm{display:inline;}

    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
</style>
	<section id="memberList-container">
       <h2>회원관리</h2>
       <div id="search-container">
        	검색타입 : 
        	<select id="searchType">
        		<option value="userId" >아이디</option>
        		<option value="userName" >회원이름</option>
        		<option value="gender" >성별</option>
        	</select>
        	<div id="search-userId">
        		<form action="${path}/admin/searchMember">
        			<input type="hidden" name="searchType" value="userId" >
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 아이디를 입력하세요" >
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	<div id="search-userName">
        		<form action="${path}/admin/searchMember">
        			<input type="hidden" name="searchType" value="userName">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 이름을 입력하세요">
        			<button type="submit">검색</button>
        		</form>
        	</div>
        	<div id="search-gender">
        		<form action="${path}/admin/searchMember">
        			<input type="hidden" name="searchType" value="gender">
        			<label><input type="radio" name="searchKeyword" value="M" >남</label>
        			<label><input type="radio" name="searchKeyword" value="F" >여</label>
        			<button type="submit">검색</button>
        		</form>
        	</div>
        </div>
        <div id="numPerpage-container">
        	페이지당 회원수 : 
        	<form id="numPerFrm" action="${path}/admin/memberList.do">
        		<select name="numPerpage" id="numPerpage" onchange="this.form.submit()">
        			<option value="10">10</option>
        			<option value="5" >5</option>
        			<option value="3" >3</option>
        		</select>
        		
        	</form>
        </div>
       <table id="tbl-member">
           <thead>
               <tr>
                   	<th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
               </tr>
           </thead>
           <tbody>
      	    	<c:if test="${not empty members}">
					<c:forEach var="member" items="${members}">
						<tr>
							<td>${member.userId}</td>
							<td>${member.userName}</td>
							<td>${member.gender}</td>
							<td>${member.age}</td>
							<td>${member.email}</td>
							<td>${member.phone}</td>
							<td>${member.address}</td>
							<td>${fn:join(member.hobby,",")}</td>
							<td><fmt:formatDate value="${member.enrollDate}" type="date" dateStyle="long"/></td>
						</tr>
					</c:forEach>           		
           		</c:if>
           		<c:if test="${empty members}">
           			<tr>
           				<td colspan="9">조회된 데이터가 없습니다.</td>
           			</tr>
           		</c:if>
           </tbody>
       </table>
       <div id="pageBar">
       		${pageBar}
       </div>
	</section>
	<script>
		$(()=>{
			$("#searchType").change((e)=>{
				const type = $(e.target).val();
				$("div#search-container>div").hide();
				$("#search-"+type).css("display","inline-block");
			})	
			$("#numPerpage").val('${param.numPerpage!=null?param.numPerpage:"10"}');
		})
	</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		