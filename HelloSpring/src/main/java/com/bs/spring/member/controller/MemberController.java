package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.model.service.MemberService;
import com.bs.spring.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member") //이 컨트롤러에 들어왔을 때 기본적으로 prefix로 /member/가 붙는다
public class MemberController {
	
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
		this.service=service;
		this.passwordEncoder=passwordEncoder;
	}
	
//	@RequestMapping("/test/")
//	public void test() {
//		System.out.println("Controller test() 실행");
//		service.test();
//	}
	
	@RequestMapping("/loginMember.do")
	//public String login(String userId, String password) {
	//public String login(@RequestParam Map param){
	//public String loginMember(Member m, HttpSession session) {
	//model은 request와 생명주기가 같지만 addAtribute를 사용하면 session과 생명주기가 같아질 수 있다.
	public String loginMember(Member m, Model model) {	
		Member loginMember = service.selectMemberById(m);
		
		//암호화된 패스워드를 원본값으링 비교하기 위해서는 BCryptPasswordEncoder 클래스가 제공하는 메소드를 이용해서 동등비교를 해야한다.
		//matches("원본값","암호화값") 메소드를 이용
		if(loginMember != null && passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			//로그인 성공
			//session.setAttribute("loginMember", loginMember);
			model.addAttribute("loginMember",loginMember);
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/logoutMember.do")
	//public String logoutMember(HttpSession session) {
	public String logoutMember(SessionStatus session) {	
		//session.invalidate();
		if(!session.isComplete()) { //세션을 확인
			session.setComplete(); //세션을 삭제
		} 
		
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(ModelAndView mv, Member m) {
		
		//password 암호화 처리하기
		String encodePassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
			
		int result = service.insertMember(m);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","회원가입 성공!! 가입하신 계정으로 로그인해주세요~");
			mv.addObject("loc","/");
		} else {
			mv.addObject("msg","회원가입 실패!! 다시 가입해주세요~");
			mv.addObject("loc","/member/enrollMember.do");
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/memberView.do")
	public String updateMember(Member m, Model model) {
		
		Member viewMember = service.selectMemberById(m);
		model.addAttribute("member",viewMember);
		
		return "member/memberView";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
