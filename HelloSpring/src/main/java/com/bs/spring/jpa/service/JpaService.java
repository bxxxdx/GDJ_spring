package com.bs.spring.jpa.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.jpa.model.entity.JpaMember;

public interface JpaService {
	
	void insertMember(JpaMember m);
	
	JpaMember selectMemberById(long memberNo);

	void updateMember(Map<String, Object> param, long memberNo);
	
	void deleteMember(long memberNo);
	
	List<JpaMember> selectMemberAll();
	
	List<JpaMember> selectMemberSearch(double height);
}
