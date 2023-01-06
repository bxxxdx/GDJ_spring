package com.bs.spring.member.model.service;

import com.bs.spring.member.model.vo.Member;

public interface MemberService {
	void test();
	
	Member selectMemberById(Member m);
	
	int insertMember(Member m);
}
