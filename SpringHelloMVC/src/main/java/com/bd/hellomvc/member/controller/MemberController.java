package com.bd.hellomvc.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bd.hellomvc.member.model.service.MemberService;
import com.bd.hellomvc.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
//@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
		this.service = service;
		this.passwordEncoder = passwordEncoder;
	}
	
	@RequestMapping("/member/enrollMember.do")
	public String enrollMember() {
		return "member/enrollmember";
	}
	

	@RequestMapping("/member/idDuplicate.do")
	public ModelAndView idDuplicate(ModelAndView mv, String userId){
		Member m = service.searchMemberId(userId);
		mv.addObject("member",m);
		mv.setViewName("member/idDuplicate");
		return mv;
	}
	
	
	@RequestMapping("/member/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(ModelAndView mv, Member m) {
		
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
	
	@RequestMapping("login.do")
	public String login(Model model, Member m) {
		Member loginMember = service.searchMemberId(m.getUserId());
		if(loginMember != null && passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			model.addAttribute("loginMember",loginMember);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/member/memberView.do")
	public ModelAndView memberView(ModelAndView mv, String id) {
		Member m = service.searchMemberId(id);
		
		//이메일 양방향 암호화... 추가 ?  
		
		mv.addObject("member",m);
		mv.setViewName("member/memberView");
		
		return mv;
	}
	
	@RequestMapping("/member/updateMember.do")
	public ModelAndView updateMember(ModelAndView mv, Model model, Member m) {
		
		int result = service.updateMember(m);
		
		if(result > 0) {
			mv.addObject("msg","회원수정 성공!!");
			mv.addObject("loc","/");
			Member updateM = service.searchMemberId(m.getUserId());
			model.addAttribute("loginMember", updateM);
		} else {
			mv.addObject("msg","회원수정 실패!!");
			mv.addObject("loc","/member/memberView.do?id="+ m.getUserId());
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/logout.do")
	public String logout(SessionStatus session) {
		if(!session.isComplete()) {
			session.setComplete();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/member/updatePassword.do")
	public String updatePassword() {
		return "member/updatePassword";
	}
	
	@RequestMapping("/member/updatePasswordEnd.do")
	public ModelAndView updatePasswordEnd(ModelAndView mv, HttpServletRequest request, Member m, String password_new) {
//		System.out.println(m);
//		System.out.println(password_new);
		
		Member loginMember = service.searchMemberId(m.getUserId());
		
		String msg = "";
		String loc = "";
		if(loginMember != null && passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) {
			String encodePassword = passwordEncoder.encode(password_new);
			m.setPassword(encodePassword);
			int result = service.updatePassword(m);
			if(result > 0) {
				msg="비밀번호 변경완료 변경된 비밀번호로 로그인해주세요!";
				loc="/logout.do";
				String script = "opener.location.replace('"+request.getContextPath()+"/logout.do');close();";
				request.setAttribute("script", script);
			} else {
				msg="비밀번호 변경실패";
				loc="/member/updatePassword.do?userId=" + m.getUserId();
			}
		} else {
			msg="기존 비밀번호가 일치하지 않습니다. 다시 시도하세요.";
			loc="/member/updatePassword.do?userId="+ m.getUserId();
		}
		mv.addObject("msg", msg);
		mv.addObject("loc", loc);
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	@RequestMapping("/member/deleteMember.do")
	public ModelAndView deleteMember(ModelAndView mv, String userId) {
		int result = service.deleteMember(userId);
		mv.addObject("msg",result>0?"들어가고~!":"넌 못가!");
		mv.addObject("loc","/logout.do");
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
