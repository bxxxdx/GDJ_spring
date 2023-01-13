<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%-- <%
	Board b = (Board)request.getAttribute("board");
	List<BoardComment> bcs = (List<BoardComment>)request.getAttribute("boardComments");
%> --%>
    
<style>
    section#board-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
     div#comment-container button#btn-insert{width:60px;height:50px; color:white;
    background-color:#3300FF;position:relative;top:-20px;}
    
    /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    /*답글관련*/
    table#tbl-comment textarea{margin: 4px 0 0 0;}
    table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
</style>
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<td>${board.boardNo}</td>
		</tr>
		<tr>
			<th>제 목</th>
			<td>${board.boardTitle}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.member.userId}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boardReadcount}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<%-- <%if(b.getBoardOriginalFileName() != null) {%>
			 		<img src="<%=request.getContextPath()%>/images/file.png" width="20" onclick="fn_fileDown('<%=b.getBoardRenamedFileName()%>');">&nbsp;<%=b.getBoardRenamedFileName()%>
				<%} else { %>
					첨부파일없음
				<%} %>  --%>
				<c:if test="${board.boardOriginalFilename != null}">
					<img src="${path}/resources/images/file.png" width="20" onclick="fn_fileDown('${board.boardOriginalFilename}', '${board.boardRenamedFilename}');">&nbsp;${board.boardOriginalFilename}
				</c:if>
				<c:if test="${board.boardOriginalFilename == null }">
					첨부파일없음
				</c:if>
			</td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>${board.boardContent}</td>
		</tr>
		<%--글작성자/관리자인경우 수정삭제 가능 --%>
		<c:if test="${loginMember!= null and (loginMember.userId==board.member.userId or loginMember.userId =='admin')}">
		<%-- <%if(loginMember != null && (loginMember.getUserId().equals(b.getBoardWriter()) || loginMember.getUserId().equals("admin"))) {%> --%>
			<tr>
				<th colspan="2">
					  <input type="button" value="수정하기" onclick="location.replace('${path}/board/updateBoard.do?boardNo=${board.boardNo}')">
			          <input type="button" value="삭제하기" onclick="fn_delete();">
				</th>
			</tr>
		<%-- <%} %> --%>
		</c:if>
	</table>
	<div id="comment-container">
		<div class="comment-editor">
			<form action="${path}/board/writeBoardComment.do" method="post">
				<textarea name="content" cols="55" rows="3"></textarea>
				<input type="hidden" name="boardref" value="${board.boardNo}">
				<input type="hidden" name="level" value="1">
				<input type="hidden" name="commentref" value="0">
				<input type="hidden" name="commentWriter" value="${loginMember!=null?loginMember.userId:''}">
				<button type="submit" id="btn-insert">등록</button>
			</form>
		</div>
		<table id="tbl-comment">
			<c:if test="${board.boardComments != null and not empty board.boardComments}">
				<c:forEach var="boardComment" items="${board.boardComments}" varStatus="vs">
					<c:if test="${boardComment.boardCommentLevel == 1}">
						<tr class="level1">
			   				<td style="width:200px">
							    <sub class="comment-writer">${boardComment.boardCommentWriter}</sub>
							    <sub class="comment-date"><fmt:formatDate value="${boardComment.boardCommentDate}" type="date" dateStyle="long"/></sub>
							    <br> 
							    ${boardComment.boardCommentContent}                 
			   				</td>
							<c:if test="${loginMember != null}">
								<td>
									<button class="btn-reply" value="${boardComment.boardCommentNo}">답글</button>
									<c:if test="${loginMember.userId==boardComment.boardCommentWriter or loginMember.userId=='admin'}">
										<button class="btn-delete" value="${boardComment.boardCommentNo}">삭제</button>
									</c:if>
								</td>
							</c:if>
							<c:if test="${loginMember == null}">
								<td></td>
							</c:if>			
						</tr>
					</c:if>
					<c:if test="${boardComment.boardCommentLevel == 2}">
						<tr class="level2">
							<td>
								<sub>${boardComment.boardCommentWriter}</sub>
								<sub><fmt:formatDate value="${boardComment.boardCommentDate}" type="date" dateStyle="long"/></sub>
								<br>
								${boardComment.boardCommentContent}
							</td>
							<td>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
		</table>
		<!-- 댓글(로그인한 사용자만), 삭제버튼만들기 (작성자, 관리자만삭제가능) -->
	</div>
</section>
<script>
	const fn_fileDown = (ori, re) => {
			if(confirm("파일을 다운로드하시겠습니까?")){
				location.assign("${path}/board/fileDown.do?ori="+ori+"&re="+re);
			}	
		}

	const fn_delete = () => {
		let result = confirm("게시물을 정말 삭제하시겠습니까?");
		if(result) {
			location.replace("${path}/board/deleteBoard.do?boardNo=${board.boardNo}");			
		}
	}
	$(() => {
		$(".comment-editor>form>textarea").focus(e=>{
			<%-- <%if(loginMember==null){%> --%>
			<c:if test="${loginMember == null}">
				alert("로그인 후 이용할 수 있습니다.");
				$("#userId").focus();
			<%-- <%}%> --%>
			</c:if>
		});
		
		$(".btn-reply").click(e=>{
			const tr=$("<tr>");
			const form=$(".comment-editor>form").clone();
			form.find("textarea").attr({"cols":"40","rows":"1"});
			form.find("input[name=level]").val("2");
			form.find("input[name=commentref]").val($(e.target).val());
			form.find("button").removeAttr("id").addClass("btn-insert2");
			const td=$("<td>").attr("colspan","2").append(form);
			tr.append(td);
			tr.find("td").css("display","none");
			tr.insertAfter($(e.target).parents("tr")).children("td").slideDown(800);
			$(e.target).off("click");
		});
		
		$(".btn-delete").click(e=>{
			location.replace("${path}/board/deleteBoardComment.do?boardCommentNo="+$(e.target).val()+"&boardNo="+${board.boardNo});
		})
		
	})
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>