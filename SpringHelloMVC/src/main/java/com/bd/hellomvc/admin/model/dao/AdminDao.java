package com.bd.hellomvc.admin.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bd.hellomvc.member.model.vo.Member;

public interface AdminDao {

	List<Member> selectMemberList(SqlSessionTemplate session, int cPage, int numPerpage);

	int selectMemberCount(SqlSessionTemplate session);
	
	List<Member> selectSearchMemberList(SqlSessionTemplate session, Map param, int cPage, int numPerpage);

	int selectSearchMemberCount(SqlSessionTemplate session, Map param);
}
