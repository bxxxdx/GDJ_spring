package com.bs.spring.jpa.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bs.spring.jpa.model.entity.Club;
import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;
import com.bs.spring.jpa.model.entity.Student;


public interface JpaDao {

	void insertMember(EntityManager em, JpaMember m);
	
	JpaMember selectMemberById(EntityManager em, long memberNo);

	void updateMember(EntityManager em, Map<String, Object> param, long memberNo);
	
	void deleteMember(EntityManager em, long memberNo);
	
	List<JpaMember> selectMemberAll(EntityManager em);
	
	List<JpaMember> selectMemberSearch(EntityManager em, double height);
	
	void insertMember(EntityManager em);
	
	Major selectMajor(EntityManager em, Long no);
	
	void insertStudentClub(EntityManager em);
	
	Student selectStudent(EntityManager em, Long no);
	
	Club selectClub(EntityManager em, Long no);
}
