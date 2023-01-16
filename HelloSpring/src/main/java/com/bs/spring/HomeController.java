package com.bs.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bs.spring.common.AdminAccessException;
import com.bs.spring.member.model.vo.Member;
import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"loginMember"})
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/* 등록되어 있는 springbean은 필드선언해서 사용 */
	
//	@Autowired
//	@Qualifier(value="alonge")
//	private Animal a;
//	
//	@Autowired
//	@Qualifier(value="song")
//	private Animal b;
//	
//	@Autowired
//	@Qualifier(value="getDongmin")
//	private Person p;
//	
//	@Autowired(required = false)
//	private Food food;

	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws IllegalAccessException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		if(1==1)throw new IllegalAccessException("내맘대로에러");
		
		return "home";
	}
	
	@RequestMapping("/")
	public String index(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		Cookie c = new Cookie("testData","cookiedata");
		c.setMaxAge(60*60*24);
		res.addCookie(c);
		session.setAttribute("sessionId", "admin");
		//등록된 springbean출력하기
//		a.setName("아롱이");
//		a.setAge(8);
//		a.setGender("여");
//		a.setWeight(180.5);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(p);
//		System.out.println(food);
		
		
		//메인 화면을 출력해주는 mapping 메소드다.
		//InternalResourceViewResolver 가 만들어서 getRequestDispatcher("").forward( , );에 붙여줌
		
		
		
		
		////////////////////////////////////////////
		// Logger가 제공하는 메소드 이용해서 log 출력하기
		// 메소드 : debug, info, warn, error
		// 메소드는 출력되는 상황에 따라 결정해서 사용
		// debug : 개발시에 사용하는 로그
		// info : 프로그램 실행중 사용자에게 전달해아하는 메시지 로그 => 회사마다 정해진 info 출력 규칙이 있다! 가서 따라하면 됨
		// warn : 비 정상적으로 로직이 돌아갔을 때 경고 로그 ( 프로그램이 문제는 없는데 요상하다 싶은 내용 )
		// error : 완전히 에러 났을 때 !! 로그

		// logger태그에 설정되어 있는 level에 따라 메소드 실행여부가 결정됨.
		// debug < info < warn < error
		// debug를 설정하면 info도 찍고 warn도 찍고 error도 찍는다
		// info를 설정하면 warn도 찍고 error도 찍는다
		
		
		//logger.debug("난 debug야 ㅋ");
		
		//logger.info("난 info야 ㅎ");
		
		//logger.warn("난 warn이야 ㅜ");
		
		//logger.error("난 error야 -_-");
		
		//logger로 다른타입의 값 출력하기
		//logger.debug(food); // Error
		//logger.debug("foor {}", food);
		
		
		
		
		
		
		

		
		return "index";
	}
	
	
	@RequestMapping("/error.do")
	public void loginFail() {
		//인증 실패 후 실행되는 메소드
		throw new AdminAccessException("로그인 실패!");
	}
	
	@RequestMapping("/successLogin.do")
	public String loginSuccess(Model m) {
		//인증 후 실행되는 메소드
		//인증정보 가져오기
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.debug("{}",o);
		
		m.addAttribute("loginMember", (Member)o );
		
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
