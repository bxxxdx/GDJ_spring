package com.bs.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.model.vo.Member;

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
	public ModelAndView insertBoard(ModelAndView mv, MultipartFile[] upFile, 
				String boardTitle, String boardContent, String boardWriter, HttpSession session) {

//		log.debug(upFile[0].getName());
//		log.debug(upFile[0].getOriginalFilename());
//		log.debug(upFile[1].getName());
//		log.debug(upFile[1].getOriginalFilename());
		log.debug(boardTitle + " " + boardContent + " " + boardWriter);
		
		//파일 업로드 처리하기
		//저장위치가져오기
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		log.debug("경로 : " + path); 
		// C:\Users\GDJ_56\git\GDJ_spring\HelloSpring\src\main\webapp\resources\ upload\board
		
		//폴더 있는지 확인하고 폴더를 생성해주기
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs();
		
		List<Attachment> files = new ArrayList();
		
		for(MultipartFile f : upFile) {
			//리네임드규칙을 생성하기
			if(!f.isEmpty()) {
				//전송된 파일이 있다면...
				//파일 리네임처리 직접하기
				String originalFileName = f.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				//중복되지 않는 이름 설정하는 값
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd = (int)(Math.random() * 10000) + 1;
				String renameFile = sdf.format(System.currentTimeMillis()) + "_" + rnd + ext;
				
				//파일 업로드하기
				try {
					//MultipartFile 클래스가 제공해주는 메소드 이용해서 저장처리
					f.transferTo(new File(path+renameFile));
					files.add(Attachment.builder()
							.originalFilename(originalFileName)
							.renamedFilename(renameFile).build());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Board b = Board.builder()
				.boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(Member.builder().userId(boardWriter).build())
				.files(files)
				.build();
		
		int result = service.insertBoard(b);
		mv.addObject("msg",result > 0?"게시글 등록 성공":"게시글 등록 실패");
		mv.addObject("loc","/board/board.do");
		
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
