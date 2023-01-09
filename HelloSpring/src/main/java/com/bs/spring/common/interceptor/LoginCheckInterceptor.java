package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bs.spring.member.model.vo.Member;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = (HttpSession)request.getSession();
		//model객체로 loginMember를 올렸어도 session.getAttribute로 가져올 수 있다. 호환 가능
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		//사실 Exception을 발생시키고 로그인에러페이지를 연결해주면 끝난다.
		//일단은 약식 예시
		if(loginMember == null) {
			request.setAttribute("msg", "로그인 후 이용할 수 있는 서비스입니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
}
