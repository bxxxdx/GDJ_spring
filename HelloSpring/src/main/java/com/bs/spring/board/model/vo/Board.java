package com.bs.spring.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.bs.spring.member.model.vo.Member;

import lombok.Data;

@Data
public class Board {
	int boardNo;
	String boardTitle;
	Member member;
	String boardContent;
	Date boardDate;
	int boardReadcount;
	List<Attachment> attachments;
}
