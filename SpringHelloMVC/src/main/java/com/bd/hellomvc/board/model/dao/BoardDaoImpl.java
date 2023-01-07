package com.bd.hellomvc.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bd.hellomvc.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> searchBoardAll(SqlSessionTemplate session, int cPage, int numPerpage) {
		return session.selectList("board.searchBoardAll", null, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		return session.selectOne("board.selectBoardCount");
	}

	@Override
	public int searchBoardCommentCount(SqlSessionTemplate session, int boardNo) {
		return session.selectOne("board.searchBoardCommentCount", boardNo);
	}

	@Override
	public Board searchBoardNo(SqlSessionTemplate session, Map param) {
		return session.selectOne("board.searchBoardNo", param);
	}

	@Override
	public int updateReadcount(SqlSessionTemplate session, int boardNo) {
		return session.update("board.updateReadcount", boardNo);
	}

	@Override
	public int insertBoard(SqlSessionTemplate session, Map param) {
		return session.insert("board.insertBoard", param);
	}

	@Override
	public int insertBoardComment(SqlSessionTemplate session, Map param) {
		return session.insert("board.insertBoardComment", param);
	}

	@Override
	public int updateBoard(SqlSessionTemplate session, Map param) {
		return session.update("board.updateBoard", param);
	}

	@Override
	public int deleteBoard(SqlSessionTemplate session, int boardNo) {
		return session.delete("board.deleteBoard", boardNo);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
