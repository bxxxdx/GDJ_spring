package com.bd.hellomvc.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.bd.hellomvc.member.model.vo.Member;

public interface MemberDao {
	
	Member searchMemberId(SqlSessionTemplate session, String userId);
	
	int insertMember(SqlSessionTemplate session, Member m);
	
	int updateMember(SqlSessionTemplate session, Member m);
	
	int updatePassword(SqlSessionTemplate session, Member m);
	
	int deleteMember(SqlSessionTemplate session, String userId);
	
}
