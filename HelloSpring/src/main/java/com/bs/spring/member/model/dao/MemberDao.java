package com.bs.spring.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.model.vo.Member;

public interface MemberDao {
	void test();
	
	Member selectMemberById(SqlSessionTemplate session, Member m);
	
	int insertMember(SqlSessionTemplate session, Member m);
	
	List<Member> selectMemberList(SqlSessionTemplate session);
	
	int selectMemberCount(SqlSessionTemplate session);
}
