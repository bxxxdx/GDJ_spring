package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.model.vo.Member;

@Component
@Aspect
public class AdminCheckAspect {

	@Before("execution(* com.bs.spring.memo..select*(..))")
	public void zz(JoinPoint jp) {
		//로그인 정보 가져오기
		//문제점 : 세션을 어떻게 가져오지??????????
		//해결 방안 : RequestContextHolder클래스에서 currentRequestAttribute() static 메소드를 사용하면 session 객체를 가져올 수 있다.
		HttpSession session = (HttpSession)RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null || !loginMember.getUserId().equals("admin")) {
			throw new AdminAccessException("관리자만 접근할 수 있습니다! :( ");
		} else {
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
