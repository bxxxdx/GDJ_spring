package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="SEQ_STUDENTNO", sequenceName="SEQ_STUDENTNO", allocationSize=1)
//json으로 파싱할 때 무한루핑 방지하기
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_STUDENTNO")
	private Long studentNo;
	
	private String studentName;
	
	private Integer grade;
	
	private Integer classNumber;
	
//	@ManyToMany
//	@JoinTable(name="STUDENT_CLUB"
//		, joinColumns=@JoinColumn(name="studentNo") // 현 Entity의 pk를 참조할 컬럼
//		, inverseJoinColumns = @JoinColumn(name="clubNo")) //연결된 상대방 Entity의 Pk를 참조할 컬럼
//	private List<Club> clubs;
	
	@OneToMany(mappedBy="student")
	private List<StudentClubs> studentClubs;
}
