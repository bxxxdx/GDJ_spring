<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="카카오로긴"/>
</jsp:include>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.1.0/kakao.min.js"
  integrity="sha384-dpu02ieKC6NUeKFoGMOKz6102CLEWi9+5RQjWSV0ikYSFFd8M3Wp2reIcquJOemx" crossorigin="anonymous">
  </script>
  
  
	<button class="api-btn" onclick="requestUserInfo()" style="visibility:hidden">사용자 정보 가져오기</button>
   	<button class="api-btn" onclick="kakaoLogout()">로그아웃</button>
   	
   	
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('d0115154323c0f0dcc665bdd8bd06b83'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {

   Kakao.Auth.login({
     success: function (response) {
       
       Kakao.API.request({
         url: '/v2/user/me',
         success: function (response) {
            //정보 넘기면서 회원가입 페이지 전환하기
            console.log(response);
            console.log(response.id);
            console.log(response.kakao_account.email);
            $("#kakaoID").val(response.id);
            
         
         },
         fail: function (error) {
           console.log(error);
         },
       })
     },
     
     fail: function (error) {
       console.log(error)
     },
   })
 }
  
//카카오로그아웃  
function kakaoLogout() {
    Kakao.Auth.logout()
      .then(function() {
        alert('logout ok\naccess token -> ' + Kakao.Auth.getAccessToken());
        deleteCookie();
      })
      .catch(function() {
        alert('Not logged in');
      });
  }
</script>



 <jsp:include page="/WEB-INF/views/common/footer.jsp"/>