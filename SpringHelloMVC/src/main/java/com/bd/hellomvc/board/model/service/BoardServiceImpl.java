package com.bd.hellomvc.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.hellomvc.board.model.dao.BoardDao;
import com.bd.hellomvc.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public BoardServiceImpl(BoardDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Board> searchBoardAll(int cPage, int numPerpage) {
		return dao.searchBoardAll(session, cPage, numPerpage);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public int searchBoardCommentCount(int boardNo) {
		return dao.searchBoardCommentCount(session, boardNo);
	}

	@Override
	public Board searchBoardNo(Map param, boolean readFlag) {
		Board board = dao.searchBoardNo(session, param);
		if(board != null && !readFlag) {
			int result = dao.updateReadcount(session, (int)param.get("boardNo"));
			if(result > 0) {
				board.setBoardReadcount(board.getBoardReadcount() + 1);
			}
		}		
		return board;
	}

	@Override
	public int insertBoard(Map param) {
		return dao.insertBoard(session, param);
	}

	@Override
	public int insertBoardComment(Map param) {
		return dao.insertBoardComment(session, param);
	}

	@Override
	public int updateBoard(Map param) {
		return dao.updateBoard(session, param);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(session, boardNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
