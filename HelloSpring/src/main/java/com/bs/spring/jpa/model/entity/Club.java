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
@SequenceGenerator(name="SEQ_CLUBNO", sequenceName="SEQ_CLUBNO", allocationSize=1)
//json으로 파싱할 때 무한루핑 방지하기
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Club {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CLUBNO")
	private Long clubNo;
	
	private String name;
	
	private String location;
	
//	@ManyToMany(mappedBy="clubs")
//	private List<Student> students;
	
	@OneToMany(mappedBy="club")
	private List<StudentClubs> studentClubs;
	
	@Override
	public String toString() {
		String temp = "";
//		for(Student s : students) {
//			temp += s.getStudentName() + " " + s.getGrade() + " " + s.getClassNumber() + " gg " ;
//		}
		return "Club [clubNo=" + clubNo + ", name=" + name + ", location=" + location + ", students=" + temp + "]";
	}

	
	
}
