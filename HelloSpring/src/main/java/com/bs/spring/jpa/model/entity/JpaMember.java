package com.bs.spring.jpa.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//생성한 클래스를 jpa와 연동하는 Entity로 등록하려면 @Entity Annotation을 이용한다.
//@Entity Annotation -> jpa 관리하는 DB와 연동되는 객체를 의미함.

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity(name="JPATEST")
@ToString(exclude = "major")
@Entity
@Table(name="JPA_MEMBER") // DB 테이블을 설정, schema, catalog 속성설정이 가능, 
//uniqueConstraint 컬럼에 대한 제약조건 설정 가능(테이블 레벨에서..), 일반적으로 안적어도 문제는 없긴 하다..
@SequenceGenerator(name="SEQ_JPAMEMBERNO", sequenceName="SEQ_JPAMEMBERNO", initialValue=1 ) // sequence를 생성하는 어노테이션
public class JpaMember {
	
	@Id //컬럼으로 생성할 때 pk값을 생성한 것
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JPAMEMBERNO") // 자동생성된 값을 대입해주는 설정 *오라클에서는 sequence를 의미
	@Column(name="MEMBER_NO") //컬럼설정
	private long memberNo;
	@Column(name="MEMBER_ID", nullable=false, unique=true, length=80)
	private String memberId;
	@Column(name="MEMBER_PWD", nullable=false, length=80)
	private String memberPwd;
	private int age;
	private double height;
	
	@Column
	@Enumerated(EnumType.STRING) // enum 타입 설정
	private MemberLevel memberLevel;
	
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
	
	@Lob
	private String intro;
	
	// @oneToMany, @ManyToOne, @OneToOne, @ManyToMany
	@ManyToOne
	@JoinColumn(name="majorNo")
	private Major major;
}
