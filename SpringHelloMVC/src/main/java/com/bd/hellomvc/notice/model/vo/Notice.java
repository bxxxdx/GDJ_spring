package com.bd.hellomvc.notice.model.vo;

import java.sql.Date;

import com.bd.hellomvc.member.model.vo.Member;

import lombok.Builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice { 
	private int noticeNo;
	private String noticeTitle;
//	private String noticeWriter; 
	private Member member;
	private String noticeContent;
	private Date noticeDate;
	private String filePath;
	private char status;
}
