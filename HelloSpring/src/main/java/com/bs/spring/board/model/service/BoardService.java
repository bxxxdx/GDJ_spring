package com.bs.spring.board.model.service;

import java.util.List;
import com.bs.spring.board.model.vo.Board;

public interface BoardService {

	List<Board> selectBoardList(int cPage, int numPerpage);
	
	int selectBoardListCount();
	
	int insertBoard(Board b);
	
	Board selectBoard(int boardNo);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
