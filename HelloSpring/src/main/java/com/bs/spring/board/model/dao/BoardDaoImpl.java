package com.bs.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerpage) {
		return session.selectList("selectBoardList", null, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectBoardListCount(SqlSessionTemplate session) {
		return session.selectOne("selectBoardListCount");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
