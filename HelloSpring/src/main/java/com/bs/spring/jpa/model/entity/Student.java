package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

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
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STUDNETNO")
	private Long studentNo;
	
	private String studentName;
	
	private Integer grade;
	
	private Integer classNumber;
	
	@ManyToMany
	@JoinTable(name="STUDENT_CLUB"
		, joinColumns=@JoinColumn(name="studentNo") // 현 Entity의 pk를 참조할 컬럼
		, inverseJoinColumns = @JoinColumn(name="clubNo")) //연결된 상대방 Entity의 Pk를 참조할 컬럼
	private List<Club> clubs;
}
