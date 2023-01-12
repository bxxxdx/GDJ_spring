package com.bs.spring.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private BoardDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public BoardServiceImpl(BoardDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Board> selectBoardList(int cPage, int numPerpage) {
		return dao.selectBoardList(session, cPage, numPerpage);
	}

	@Override
	public int selectBoardListCount() {
		return dao.selectBoardListCount(session);
	}

	@Override
	@Transactional
	public int insertBoard(Board b) {
		//1. 게시글을 등록하고
		//2. 첨부파일을 등록
		
		log.debug("insert 후" + b.getBoardNo());
		int result = dao.insertBoard(session, b);
		log.debug("insert 후" + b.getBoardNo());
		
		//int boardNo = dao.selectBoardSeq(session);
		//b.setBoardNo(boardNo);
		if(result > 0) {
			for(Attachment a : b.getFiles()) {
				a.setBoard(b);
				result = dao.insertAttachment(session, a);
			}
		}
		return result;
	}

	@Override
	public Board selectBoard(int boardNo) {
		return dao.selectBoard(session, boardNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
