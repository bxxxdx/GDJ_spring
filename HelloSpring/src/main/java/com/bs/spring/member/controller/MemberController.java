package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.model.service.MemberService;
import com.bs.spring.member.model.vo.Member;

@Controller
@RequestMapping("/member") //이 컨트롤러에 들어왔을 때 기본적으로 prefix로 /member/가 붙는다
public class MemberController {
	
	
	private MemberService service;
	
	@Autowired
	public MemberController(MemberService service) {
		this.service=service;
	}
	
//	@RequestMapping("/test/")
//	public void test() {
//		System.out.println("Controller test() 실행");
//		service.test();
//	}
	
	@RequestMapping("/loginMember.do")
	//public String login(String userId, String password) {
	//public String login(@RequestParam Map param){
	public String loginMember(Member m, HttpSession session) {	
		Member loginMember = service.selectMemberById(m);
		
		//System.out.println(loginMember);
		
		if(loginMember != null && loginMember.getPassword().equals(m.getPassword())) {
			//로그인 성공
			session.setAttribute("loginMember", loginMember);
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping("/logoutMember.do")
	public String logoutMember(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(ModelAndView mv, Member m) {
		
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
