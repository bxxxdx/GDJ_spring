package com.bd.hellomvc.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bd.hellomvc.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member searchMemberId(SqlSessionTemplate session, String userId) {
		return session.selectOne("member.searchMemberId", userId);
	}

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		return session.insert("member.insertMember", m);
	}

	@Override
	public int updateMember(SqlSessionTemplate session, Member m) {
		return session.update("member.updateMember", m);
	}

	@Override
	public int updatePassword(SqlSessionTemplate session, Member m) {
		return session.update("member.updatePassword", m);
	}

	@Override
	public int deleteMember(SqlSessionTemplate session, String userId) {
		return session.delete("member.deleteMember", userId);
	}
	
	
}
