package com.bd.hellomvc.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bd.hellomvc.member.model.vo.Member;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Override
	public List<Member> selectMemberList(SqlSessionTemplate session, int cPage, int numPerpage) {
		return session.selectList("admin.selectMemberList", null, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectMemberCount(SqlSessionTemplate session) {
		return session.selectOne("admin.selectMemberCount");
	}

	@Override
	public List<Member> selectSearchMemberList(SqlSessionTemplate session, Map param, int cPage, int numPerpage) {
		return session.selectList("admin.selectSearchMemberList", param, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectSearchMemberCount(SqlSessionTemplate session, Map param) {
		return session.selectOne("admin.selectSearchMemberCount", param);
	} 
	
	

}
