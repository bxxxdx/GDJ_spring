package com.bs.spring.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.model.dao.MemberDaoImpl;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDaoImpl dao;

	@Override
	public void test() {
		System.out.println("service test() 실행");
		dao.test();
	}
	
}
