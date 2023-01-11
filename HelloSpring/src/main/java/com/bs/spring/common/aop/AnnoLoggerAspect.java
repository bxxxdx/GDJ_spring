package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;


//어노테이션으로 aop 적용하기
@Component
@Aspect
@Slf4j
public class AnnoLoggerAspect {
	
	//Pointcut 등록하기
	@Pointcut("execution(* com.bs.spring.member..*(..))")
	public void memberLogger() {}
	
	//advisor 등록
	@Before("memberLogger()")
	public void loggerBefore(JoinPoint jp) {
		
		Signature sig = jp.getSignature();
		log.debug(sig.getName() + " 실행 전");
		log.debug(sig.getDeclaringTypeName());
		log.debug("파라미터 데이터 받아오기");
		Object[] params = jp.getArgs();
		if(params != null) {
			for(Object o : params) {
				log.debug("파라미터 : {}",o);
			}
		}
		log.debug("=========================================================");
	}
	
	@After("memberLogger()")
	public void loggerAfter(JoinPoint jp) {
		Signature sig = jp.getSignature();
		log.debug(sig.getName() + " 실행 후");
		log.debug(sig.getDeclaringTypeName());
		log.debug("=========================================================");
	}
	
	//실행 전후 모두 실행하는 메소드
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demoLogger() {}
	
	@Around("demoLogger()")
	public Object demoLoggerAround(ProceedingJoinPoint join) throws Throwable {
		//궁금점 : 그럼 두번 열리는건가?
		
		//실행 전, 후를 구분하는 메소드 -> join.proceed();
		//성능 측정용 StopWatch 선언
		StopWatch stop = new StopWatch();
		stop.start();
		Object[] params = join.getArgs();
		Signature sig = join.getSignature();
		
		//proceed 기준으로 전, 후가 된다. 그 점을 표현하기 위해 StopWatch를 사용한거임.
		Object obj = join.proceed();
		stop.stop();
		log.debug("실행시간 : " + stop.getTotalTimeMillis()+"ms");
		
		
		//join.proceed()의 반환값을 return값으로 넣어주어야 한다.
		return obj;
	}
	
	
	//어떤 Exception이 발생하면 처리해 줌.
	@AfterThrowing("execution(* com.bs.spring..*(..))")
	public void exceptionTest(JoinPoint jp) {
		
		log.debug("에러발생 !!!!! ㅋ");
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
