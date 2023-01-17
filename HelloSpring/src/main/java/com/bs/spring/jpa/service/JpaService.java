package com.bs.spring.jpa.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.Student;

public interface JpaService {
	
	void insertMember(JpaMember m);
	
	JpaMember selectMemberById(long memberNo);

	void updateMember(Map<String, Object> param, long memberNo);
	
	void deleteMember(long memberNo);
	
	List<JpaMember> selectMemberAll();
	
	List<JpaMember> selectMemberSearch(double height);
	
	void insertMember();
	
	Major selectMajor(Long no);
	
	void insertStudentClub();
	
	Student selectStudent(Long no);
	
	Club selectClub(Long no);
}
