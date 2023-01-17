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
  
  
	<a id="kakao-login-btn" href="javascript:loginWithKakao()">
	  	<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
	</a>
   	<button class="api-btn" onclick="kakaoLogout()">로그아웃</button>
   	
   	
   	<a class="p-2" href="https://kauth.kakao.com/oauth/authorize?client_id=21eb5423bf0d7b2bf791888801885425
&redirect_uri=http://localhost:9090/hellomvc/kakaoTest&response_type=code">
  		카카오로긴
	</a>
   	
   	<a href="https://kauth.kakao.com/oauth/logout?client_id=21eb5423bf0d7b2bf791888801885425&logout_redirect_uri=http://localhost:9090/hellomvc/kakaoTest">로그아웃</a>
   	
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('d0115154323c0f0dcc665bdd8bd06b83'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단

//카카오로그인

	function loginWithKakao() {
	    Kakao.Auth.authorize({
	      redirectUri: 'https://developers.kakao.com/tool/demo/oauth',
	    });
	}

	function loginWithKakao() {
	    Kakao.Auth.login({
	      success : function(response) {
	        Kakao.API.request({
	          url:'/v2/user/me',
	          success: function(result) {
	        	console.log(Kakao.Auth.getAccessToken());
	            var accessToken = Kakao.Auth.getAccessToken(); // 액세스 토큰 할당
	            Kakao.Auth.setAccessToken(accessToken); // 액세스 토큰 사용하게 등록
	          },
            fail : function(error) {
              console.log('카카오톡과 연결이 완료되지 않았습니다. \n다시 시도해주시기 바랍니다.');
            }
          })
        },
        fail : function(error) {
          console.log('카카오톡과 연결 실패하였습니다. \n다시 시도해주시기 바랍니다.');
        },
      })  
    }


	
  
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

	  // 아래는 데모를 위한 UI 코드입니다.
	  function deleteCookie() {
	    document.cookie = 'authorize-access-token=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
	  }
</script>



 <jsp:include page="/WEB-INF/views/common/footer.jsp"/>