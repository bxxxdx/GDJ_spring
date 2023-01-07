package com.bd.hellomvc.member.model.service;

import com.bd.hellomvc.member.model.vo.Member;

public interface MemberService {
	Member searchMemberId(String userId);
	
	int insertMember(Member m);

	int updateMember(Member m);
	
	int updatePassword(Member m);
	
	int deleteMember(String userId);
}
