package com.bs.spring.jpa.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bs.spring.jpa.model.entity.JpaMember;


public interface JpaDao {

	void insertMember(EntityManager em, JpaMember m);
	
	JpaMember selectMemberById(EntityManager em, long memberNo);

	void updateMember(EntityManager em, Map<String, Object> param, long memberNo);
	
	void deleteMember(EntityManager em, long memberNo);
	
	List<JpaMember> selectMemberAll(EntityManager em);
	
}
