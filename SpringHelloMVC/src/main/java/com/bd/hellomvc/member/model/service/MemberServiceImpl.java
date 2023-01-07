package com.bd.hellomvc.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.hellomvc.member.model.dao.MemberDao;
import com.bd.hellomvc.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public MemberServiceImpl(MemberDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public Member searchMemberId(String userId) {
		return dao.searchMemberId(session, userId);
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}

	@Override
	public int updateMember(Member m) {
		return dao.updateMember(session, m);
	}

	@Override
	public int updatePassword(Member m) {
		return dao.updatePassword(session, m);
	}

	@Override
	public int deleteMember(String userId) {
		return dao.deleteMember(session, userId);
	}

}
