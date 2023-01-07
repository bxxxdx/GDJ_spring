package com.bd.hellomvc.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bd.hellomvc.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> searchBoardAll(SqlSessionTemplate session, int cPage, int numPerpage);
	
	int selectBoardCount(SqlSessionTemplate session);
	
	int searchBoardCommentCount(SqlSessionTemplate session, int boardNo);
	
	Board searchBoardNo(SqlSessionTemplate session, Map param);
	
	int updateReadcount(SqlSessionTemplate session, int boardNo);
	
	int insertBoard(SqlSessionTemplate session, Map param);
	
	int insertBoardComment(SqlSessionTemplate session, Map param);
	
	int updateBoard(SqlSessionTemplate session, Map param);
	
	int deleteBoard(SqlSessionTemplate session, int boardNo);
	
	
	
	
}
