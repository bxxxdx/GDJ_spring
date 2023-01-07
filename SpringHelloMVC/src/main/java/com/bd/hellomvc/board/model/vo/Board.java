package com.bd.hellomvc.board.model.vo;

import java.sql.Date;
import java.util.List;
import com.bd.hellomvc.member.model.vo.Member;
import lombok.Data;

@Data
public class Board { 
	private int boardNo;
	private String boardTitle;
	//private String boardWriter;
	private Member member;
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenamedFilename;
	private Date boardDate;
	private int boardReadcount;
	private List<BoardComment> boardComments;
}
