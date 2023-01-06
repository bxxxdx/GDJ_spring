package com.bs.spring.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
	private String name; 
	private int age;
	private String gender;
	private double weight;
	
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public void initTest() {
		System.out.println("생성 후 실행");
	}
	
	public void destroyTest() {
		System.out.println("객체 소멸 후 실행");
	}
}
