package com.bs.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.model.dao.MemberDaoImpl;
import com.bs.spring.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	private MemberDaoImpl dao;
	private SqlSessionTemplate session;
	
	//Alt + Shift + S + O
	@Autowired
	public MemberServiceImpl(MemberDaoImpl dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public void test() {
		System.out.println("service test() 실행");
		dao.test();
	}

	@Override
	public Member selectMemberById(Member m) {
		return dao.selectMemberById(session, m);
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}
	
}
