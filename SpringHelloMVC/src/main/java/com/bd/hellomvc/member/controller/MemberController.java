package com.bd.hellomvc.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bd.hellomvc.member.model.service.MemberService;
import com.bd.hellomvc.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	
	@Autowired
	public MemberController(MemberService service) {
		this.service = service;
	}
	
//	public String login(Member m, Model model) {
//		
//	}
	
	
}
