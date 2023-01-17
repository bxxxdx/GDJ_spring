package com.bs.spring.jpa.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.MemberLevel;
import com.bs.spring.jpa.model.entity.Student;
import com.bs.spring.jpa.service.JpaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JpaController {

	private JpaService service;

	@Autowired
	public JpaController(JpaService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/jpa/insert")
	public String insertMember(String userId) {
		
		JpaMember m = JpaMember.builder().memberId(userId).memberPwd("1234")
				.memberLevel(MemberLevel.GOLD).age(19).height(180.5).enrollDate(new Date())
				.intro("하하하 벌써 1월 반이 지났네..").build();
		service.insertMember(m);
		
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/members")
	public String selectMemberAll() {
		List<JpaMember> list = service.selectMemberAll();
		list.stream().forEach(v->log.debug("멤버들 : {}",v));
		return "redirect:/";
	}
	
	
	@RequestMapping("/jpa/member")
	public String selectMemberById(long no) {
		
		JpaMember m = service.selectMemberById(no);
		
		log.debug("멤바 : {}",m);
		
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/update")
	public String updateMember(long no, int age, double height, String intro) {
		Map param = Map.of("age",age,"height",height,"intro",intro);
		
		service.updateMember(param, no);
		
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/delete")
	public String deleteMember(long no) {
		service.deleteMember(no);
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/member/search")
	public String selectMemberSearch(double height) {
		List<JpaMember> list = service.selectMemberSearch(height);
		list.stream().forEach(v->log.debug("조건검색 멤버들 : {}",v));
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/insertMember")
	public String insertMember() {
		
		service.insertMember();
		
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/major")
	public String selectMajor(Long no) {
		Major major = service.selectMajor(no);
		log.debug("{}",major);
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/insertStudentClub")
	public String insertStudentClub() {
		service.insertStudentClub();
		return "redirect:/";
	}
	
	@RequestMapping("/jpa/selectStudent")
	@ResponseBody
	public Student selectStudent(Long no) {
		return service.selectStudent(no);
	}
	
	@RequestMapping("/jpa/selectClub")
	@ResponseBody 
	public Club selectClub(Long no) {
		return service.selectClub(no);
	}
	
}
