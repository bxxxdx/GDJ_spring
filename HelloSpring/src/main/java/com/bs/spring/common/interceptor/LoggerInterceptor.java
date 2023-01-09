package com.bs.spring.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//public class LoggerInterceptor implements HandlerInterceptor{
public class LoggerInterceptor extends HandlerInterceptorAdapter{
	
	//@Slf4j가 없으면 이렇게도 쓸 수 있다~
	//private final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
	//상황에 따른 실행할 메소드를 재정의
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		
		log.debug("-------- 전처리 인터셉터 실행 -----------");
		log.debug("메소드 실행전");
		log.debug(req.getRequestURI());
		Map param = req.getParameterMap();
		for(Object o : param.keySet()) {
			log.debug("{}",o + ": " + param.get(o));
		}
		log.debug("-------------------------------------");
		//응답메세지 작성하기
//		res.setContentType("text/html;charset=utf-8");
//		res.getWriter().append("<h2>interceptor가 응답함</h2>");
		
		
		//Object handler 이용
		HandlerMethod method = (HandlerMethod)handler;
		//실행되는 클래스 확인하기
		log.debug("{}", (DemoController)method.getBean());
		//실행되는 메소드 확인하기
		log.debug("{}", method.getMethod());

		// 반환값이 true면 controller 메소드를 실행
		// 반환값이 false면 controller 메소드를 실행하지 않음.
		return true;
//		return false;
	}
	
	
	//매핑 메소드의 실행이 끝난 뒤에 실행되는 메소드
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mv) throws Exception {
		
		log.debug("-------- 후처리 인터셉터 실행 --------");
		log.debug("요청 주소 {}", req.getRequestURI());
		log.debug("응답화면 : {}", mv.getViewName());
		log.debug("전송데이터 : {}", mv.getModelMap());
		log.debug("---------------------------------");
		
		//중단 시키고 싶을때 ? 
		//throw new IllegalAccessError("잘못된 접근입니다!");
		
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		log.debug("-------- 응답 후  인터셉터 실행 --------");
		log.debug("요청 주소 {}", request.getRequestURI());
		log.debug("에러메세지 : {}", ex!=null?ex.getMessage():"성공!");
		log.debug("---------------------------------");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
