package com.bs.spring.security;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.vo.Member;

public class SecurityService implements UserDetailsService{
	
	private MemberDao dao;
	private SqlSessionTemplate session;
	
	
	@Autowired
	public SecurityService(MemberDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member m = dao.selectMemberById(session, Member.builder().userId(username).build());
		
		//로그인 실패시 처리
		if(m==null) throw new UsernameNotFoundException(username);
		
		return m;
	}

}
