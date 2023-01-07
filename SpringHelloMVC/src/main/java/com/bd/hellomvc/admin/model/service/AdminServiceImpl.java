package com.bd.hellomvc.admin.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.hellomvc.admin.model.dao.AdminDao;
import com.bd.hellomvc.member.model.vo.Member;

@Service
public class AdminServiceImpl implements AdminService {
	private AdminDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public AdminServiceImpl(AdminDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Member> selectMemberList(int cPage, int numPerpage) {
		return dao.selectMemberList(session, cPage, numPerpage);
	}

	@Override
	public int selectMemberCount() {
		return dao.selectMemberCount(session);
	}

	@Override
	public List<Member> selectSearchMemberList(Map param, int cPage, int numPerpage) {
		return dao.selectSearchMemberList(session, param, cPage, numPerpage);
	}

	@Override
	public int selectSearchMemberCount(Map param) {
		return dao.selectSearchMemberCount(session, param);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
