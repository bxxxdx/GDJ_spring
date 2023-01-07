package com.bd.hellomvc.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bd.hellomvc.board.model.service.BoardService;
import com.bd.hellomvc.board.model.vo.Board;

@Controller
public class BoardController {
	private BoardService service;
	
	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView boardList(ModelAndView mv, HttpServletRequest request,
			@RequestParam(value="cPage", defaultValue="1")int cPage) {
		
		int numPerpage = 5;
		
		List<Board> boards = service.searchBoardAll(cPage, numPerpage);
		
		int totalData = service.selectBoardCount();
		
		String pageBar = "";
		int pageBarSize = 5;
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		
		int pageNo = (cPage-1)/pageBarSize*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		if(pageNo==1) {
			pageBar += "<span>[이전] </span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardList.do?cPage="+(pageNo-1)+"'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+" </span>";
			} else { 
				pageBar += "<a href='"+request.getContextPath()+"/board/boardList.do?cPage="+pageNo+"'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span> [다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/board/boardList.do?cPage="+pageNo+"'> [다음]</a>";
		}
		
		mv.addObject("boards", boards);
		mv.addObject("pageBar", pageBar);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@RequestMapping("/board/readBoard.do")
	public ModelAndView readBoard(ModelAndView mv, int boardNo,
				@CookieValue(value="boardRead", required = false)String boardRead,
				HttpServletResponse response) {
		boolean readFlag = false;
		if(boardRead != null && boardRead.contains("|"+ boardNo + "|")) {
			readFlag = true;
		}
		if(!readFlag) {
			Cookie c = new Cookie("boardRead", (boardRead+ "|" + boardNo + "|"));
			c.setMaxAge(60*60*24);
			response.addCookie(c);
		}
		
		int boardCommentCount = service.searchBoardCommentCount(boardNo);
		
		Map param = new HashMap();
		param.put("boardNo", boardNo);
		param.put("boardCommentCount", boardCommentCount);
		
		Board board = service.searchBoardNo(param, readFlag);
		
		mv.addObject("board", board);
		mv.setViewName("board/readBoard");
		
		return mv;
	}
	
	@RequestMapping("/board/writeBoard.do")
	public String writeBoard() {
		return "board/writeBoard";
	}
	
	@RequestMapping("/board/writeBoardEnd.do")
	public ModelAndView writeBoardEnd(ModelAndView mv,
				@RequestParam Map param) {
		// 파일 업로드 부분 생략.. 채워넣어야함 !
		
		int result = service.insertBoard(param);
		
		if(result > 0) {
			mv.addObject("msg","게시판 등록 성공!!");
			mv.addObject("loc","/board/boardList.do");
		} else {
			mv.addObject("msg","게시판 등록 실패!!");
			mv.addObject("loc","/board/writeBoard.do");
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	@RequestMapping("/board/writeBoardComment.do")
	public ModelAndView writeBoardComment(ModelAndView mv,
			@RequestParam(value="boardref")int boardRef,
			@RequestParam(value="content")String boardCommentContent,
			@RequestParam(value="level")int boardCommentLevel,
			@RequestParam(value="commentWriter")String boardCommentWriter,
			@RequestParam(value="commentref")int boardCommentRef) {
		
		Map param = new HashMap();
		param.put("boardRef", boardRef);
		param.put("boardCommentContent", boardCommentContent);
		param.put("boardCommentLevel", boardCommentLevel);
		param.put("boardCommentWriter", boardCommentWriter);
		param.put("boardCommentRef", boardCommentRef);
		
		int result = service.insertBoardComment(param);
		
		if(result > 0) {
			mv.addObject("msg","댓글 등록 성공!!");
		} else {
			mv.addObject("msg","댓글 등록 실패!!");
		}
		mv.addObject("loc","/board/readBoard.do?boardNo="+boardRef);
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public ModelAndView updateBoard(ModelAndView mv, int boardNo) {
		
		Map param = new HashMap();
		param.put("boardNo", boardNo);
		Board b = service.searchBoardNo(param, true);
		
		mv.addObject("board", b);
		mv.setViewName("board/updateBoard");
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoardEnd.do")
	public ModelAndView updateBoardEnd(ModelAndView mv, @RequestParam Map param) {
		
		int result = service.updateBoard(param);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","게시물 수정 성공!!");
			mv.addObject("loc","/board/boardList.do");
		} else {
			mv.addObject("msg","게시물 수정 실패!!");
			mv.addObject("loc","/board/updateNotice.do?noticeNo="+param.get("boardNo"));
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public ModelAndView deleteBoard(ModelAndView mv, int boardNo) {
		
		int result = service.deleteBoard(boardNo);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","게시물 삭제 성공!!");
			mv.addObject("loc","/board/boardList.do");
		} else {
			mv.addObject("msg","게시물 삭제 실패!!");
			mv.addObject("loc","/board/readBoard.do?boardNo="+boardNo);
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	@RequestMapping("/board/deleteBoardComment.do")
	public ModelAndView deleteBoardComment(ModelAndView mv, int boardNo, int boardCommentNo) {
		
		int result = service.deleteBoardComment(boardCommentNo);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","댓글 삭제 성공!!");
		} else {
			mv.addObject("msg","댓글 삭제 실패!!");
		}
		mv.addObject("loc","/board/readBoard.do?boardNo="+boardNo);
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	
	
	
}
