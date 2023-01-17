package com.bs.spring.jpa.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.model.entity.JpaMember;
import com.bs.spring.jpa.model.entity.Major;

@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void insertMember(EntityManager em, JpaMember m) {
		//매개변수로 전달받은 멤버변수 저장하기
		em.persist(m); // 영속성 컨테스트에 매개변수의 entity를 저장 service에서 commit 시 flush 가 되면서 db에 저장된다.
	}

	@Override
	public JpaMember selectMemberById(EntityManager em, long memberNo) {
		return em.find(JpaMember.class, memberNo);
	}

	@Override
	public void updateMember(EntityManager em, Map<String, Object> param, long memberNo) {

		JpaMember m = em.find(JpaMember.class, memberNo);
		m.setAge((int)param.get("age"));
		m.setHeight((double)param.get("height"));
		m.setIntro((String)param.get("intro"));
		
		em.persist(m);
	}

	@Override
	public void deleteMember(EntityManager em, long memberNo) {
		
		JpaMember m = em.find(JpaMember.class, memberNo);
		
		em.remove(m);

	}

	@Override
	public List<JpaMember> selectMemberAll(EntityManager em) {
		//전체조회하는 메소드는 제공하지 않음.... 쿼리문을 써야함
		//JPQL이라는 구문을 이용해서 조회해야함.
		//JPQL이란 JAVA방식의 SQL문 작성하는 방법. sql문 비슷함, from 뒷부분 엔티티명 (대소문자 구분), m은 별칭
		return em.createQuery("select m from JpaMember m", JpaMember.class).getResultList();
	}

	@Override
	public List<JpaMember> selectMemberSearch(EntityManager em, double height) {
		return em.createQuery("select m from JpaMember m where height >= :param").setParameter("param", height).getResultList();
	}

	@Override
	public void insertMember(EntityManager em) {
		JpaMember m1 = JpaMember.builder().memberId("donghun").memberPwd("1234").age(27).height(180.3).enrollDate(new Date()).intro("우리반반장").build();
		JpaMember m2 = JpaMember.builder().memberId("nari").memberPwd("3333").age(29).height(167.3).enrollDate(new Date()).intro("asdasd").build();
		
		Major major = Major.builder().majorName("코딩").professor("유병승").build();
		
		m1.setMajor(major);
		m2.setMajor(major);
		
		em.persist(major);
		
		em.persist(m1);
		em.persist(m2);
		
	}

	@Override
	public Major selectMajor(EntityManager em, Long no) {
		return em.find(Major.class, no);
	}

}
