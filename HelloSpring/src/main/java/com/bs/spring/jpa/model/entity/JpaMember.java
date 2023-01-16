package com.bs.spring.jpa.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//생성한 클래스를 jpa와 연동하는 Entity로 등록하려면 @Entity Annotation을 이용한다.
//@Entity Annotation -> jpa 관리하는 DB와 연동되는 객체를 의미함.

@Data
@Entity
public class JpaMember {
	
	@Id
	private long memberNo;
	private String memberId;
	private String memberPwd;
	private int age;
	private double height;
	private Date enrollDate;
}
