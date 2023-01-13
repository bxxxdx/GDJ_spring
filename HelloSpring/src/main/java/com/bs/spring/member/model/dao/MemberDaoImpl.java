package com.bs.spring.member.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Override
	public void test() {
		System.out.println("dao test() 실행");
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Member m) {
		return session.selectOne("member.selectMemberById", m);
	}

	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		return session.insert("member.insertMember", m);
	}

	@Override
	public List<Member> selectMemberList(SqlSessionTemplate session) {
		return session.selectList("member.selectMemberList");
	}

	@Override
	public int selectMemberCount(SqlSessionTemplate session) {
		return session.selectOne("member.selectMemberCount");
	}
	
}
