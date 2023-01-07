package com.bd.hellomvc.board.model.service;

import java.util.List;
import java.util.Map;

import com.bd.hellomvc.board.model.vo.Board;

public interface BoardService {
	
	List<Board> searchBoardAll(int cPage, int numPerpage);
	
	int selectBoardCount();
	
	int searchBoardCommentCount(int boardNo);
	
	Board searchBoardNo(Map param, boolean readFlag);
	
	int insertBoard(Map param);
	
	int insertBoardComment(Map param);
	
	int updateBoard(Map param);
	
	int deleteBoard(int boardNo);
	
	int deleteBoardComment(int boardCommentNo);
	
	
	
	
	
	
	
}
