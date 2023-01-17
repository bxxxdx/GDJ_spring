package com.bs.spring.jpa.model.entity;

import java.io.Serializable;

import lombok.Data;

//복합키 식별자 클래스로, 복합키 식별자 클래스는 조건이 있다.
//1. 기본생성자가 반드시 있어야 한다. (JPA가 이용하는녀석이기 때문에)
//2. 클래스가 public으로 선언되어야 한다.
//3. Serializable 인터페이스를 구현해야 한다.
//4. equals, hashCode 메소드가 Overriding 되어 있어야 한다.

@Data
public class StudentClubsId implements Serializable{
	//얘는 Student, Club 클래스를 복합키로 연결하는 클래스
	private long student; //StudentClub 클래스의 student 클래스의 필드명
	private long club; //StudentClub 클래스의 club 클래스의 필드명
}
