package com.bs.spring.board.model.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import com.bs.spring.board.model.vo.Board;

public interface BoardDao {

	List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerpage);
	
	int selectBoardListCount(SqlSessionTemplate session);
	
	int insertBoard(SqlSessionTemplate session, Board b);
	
	Board selectBoard(SqlSessionTemplate session, int boardNo);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
