package com.bs.spring.jpa.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@SequenceGenerator(name="SEQ_MAJORNO", sequenceName="SEQ_MAJORNO", initialValue=1, allocationSize=1)
public class Major {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_MAJORNO")
	private Long majorNo;
	private String majorName;
	private String professor;
	
	//연관관계를 설정하는 어노테이션을 추가
	//서브 클래스(FK를 갖고 있지 않음, JpaMember 메인 클래스인듯?)
	@OneToMany(mappedBy="major") //주 클래스의 참조필드명을 mappedBy에 작성하면 됨
	private List<JpaMember> members;

	
	
}
