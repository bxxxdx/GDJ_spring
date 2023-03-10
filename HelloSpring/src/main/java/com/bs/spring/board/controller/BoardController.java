package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
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
		
		log.debug("????????? : {}",service.selectBoard(boardNo));
		
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
		
		//?????? ????????? ????????????
		//????????????????????????
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		log.debug("?????? : " + path); 
		// C:\Users\GDJ_56\git\GDJ_spring\HelloSpring\src\main\webapp\resources\ upload\board
		
		//?????? ????????? ???????????? ????????? ???????????????
		File dir = new File(path);
		if(!dir.exists()) dir.mkdirs();
		
		List<Attachment> files = new ArrayList();
		
		for(MultipartFile f : upFile) {
			//????????????????????? ????????????
			if(!f.isEmpty()) {
				//????????? ????????? ?????????...
				//?????? ??????????????? ????????????
				String originalFileName = f.getOriginalFilename();
				String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
				//???????????? ?????? ?????? ???????????? ???
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd = (int)(Math.random() * 10000) + 1;
				String renameFile = sdf.format(System.currentTimeMillis()) + "_" + rnd + ext;
				
				//?????? ???????????????
				try {
					//MultipartFile ???????????? ??????????????? ????????? ???????????? ????????????
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
		mv.addObject("msg",result > 0?"????????? ?????? ??????":"????????? ?????? ??????");
		mv.addObject("loc","/board/board.do");
		
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	@RequestMapping("/fileDown.do")
	public void fileDown(String ori, String re, 
			HttpServletResponse response, HttpSession session,
			@RequestHeader(value="User-agent") String header) {
		//User-agent ??? ????????? ??? ????????? ????????? ?????? ?????? ?????????
		//HttpSession??? ??????????????? ?????? ?????????
		log.debug(ori + " / " + re);
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile = new File(path + re);
		try(FileInputStream fis = new FileInputStream(downloadFile)){
			BufferedInputStream bis = new BufferedInputStream(fis);
			//???????????? ?????? ?????????
			ServletOutputStream sos = response.getOutputStream();
			//????????? ???????????????
			boolean isMS = header.contains("Trident")||header.contains("MSIE");
			String encodeFilename = "";
			if(isMS) {
				encodeFilename = URLEncoder.encode(ori, "UTF-8");
				encodeFilename = encodeFilename.replaceAll("\\+", "%20");
			} else {
				//?????????????????? ????????????!
				encodeFilename = new String(ori.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""+encodeFilename+"\"");
			
			int read = -1;
			while((read = bis.read())!= -1) {
				sos.write(read);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
