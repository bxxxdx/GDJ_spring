package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;

import lombok.extern.slf4j.Slf4j;

//Aspect로 등록하여 멤버메소드를 특정시점에 실행시키기
@Slf4j
public class LoggerAspect {

	//aspect 클래스에서 메소드를 선언할때는 지정 advisor에 따라 메소드 선언방식이 달라짐
	//타겟 메소드가 실행되기 전에 실행하는 메소드 구현하기
	public void loggerBefore(JoinPoint jp) { //전이나 후에 실행할 메소드 선언
		
		log.debug("loggerAspect 실행함");
		
	}
	
}
