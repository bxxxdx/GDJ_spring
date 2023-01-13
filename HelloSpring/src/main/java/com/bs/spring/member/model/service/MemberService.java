package com.bs.spring.member.model.service;

import java.util.List;

import com.bs.spring.member.model.vo.Member;

public interface MemberService {
	void test();
	
	Member selectMemberById(Member m);
	
	int insertMember(Member m);
	
	List<Member> selectMemberList();
	
	int selectMemberCount();
}
