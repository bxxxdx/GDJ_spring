package com.bs.spring.member.model.vo;

import java.sql.Date;
import java.util.List;

import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
}
