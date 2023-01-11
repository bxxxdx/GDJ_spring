package com.bs.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
	
	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	@RequestMapping("/board.do")
	public ModelAndView board(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1")int cPage,
			@RequestParam(value="numPerpage", defaultValue="5")int numPerpage) {
		
		List<Board> boards = service.selectBoardList(cPage, numPerpage);
		mv.addObject("boards", boards);
		//log.debug("boards : {}", boards);
		int totalData = service.selectBoardListCount();
		//log.debug("{}",totalData);
		mv.addObject("totalContents", totalData);
		mv.addObject("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "board.do"));
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping("/boardView.do")
	public ModelAndView selectBoard(ModelAndView mv, int boardNo) {
		
		mv.addObject("board",service.selectBoard(boardNo));
		mv.setViewName("board/boardView");
		
		log.debug("보두뷰 : {}",service.selectBoard(boardNo));
		
		return mv;
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@RequestMapping("/insertBoard.do")
	public ModelAndView insertBoard(ModelAndView mv,
				@RequestParam(value="boardWriter")String boardWriter, Board b) {
		log.debug(boardWriter);
		log.debug("입력 board 값 : {}", b);
		
		
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
