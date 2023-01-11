package com.bs.spring.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.bs.spring.member.model.vo.Member;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private String boardTitle;
	private Member boardWriter;
	private String boardContent;
	private Date boardDate;
	private int boardReadcount;
	private List<Attachment> files;
}
