package com.bs.spring.memo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	private MemoService service;
	
	@Autowired
	public MemoController(MemoService service) {
		this.service = service;
	}
	
	//Get방식으로 보낼때만 받겠다, post방식은 받지 않겠다는 의미.
	@RequestMapping(value = "/memo.do", method = {RequestMethod.GET})
	public ModelAndView memoList(ModelAndView mv,
			@RequestParam(value="cPage", defaultValue="1")int cPage,
			@RequestParam(value="numPerpage", defaultValue="5")int numPerpage) {

		mv.addObject("memos", service.selectMemoListPage(Map.of("cPage",cPage,"numPerpage",numPerpage)));
		
		//페이징 처리하기
		int totalData = service.selectMemoListCount();
		mv.addObject("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "memo.do"));
		
		mv.setViewName("memo/memoList");
		
		return mv;
	}
	
//	@RequestMapping("/insertMemo.do")
//	public String insertMemo() {
//		return "memo/insertMemo";
//	}
	
	//@RequestMapping("/insertMemoEnd.do")
	@RequestMapping(value = "/insertMemo.do", method = {RequestMethod.POST})
	public ModelAndView insertMemo(ModelAndView mv, Memo m) {
		
		log.debug("입력메모 : {}", m);
		
		int result = service.insertMemo(m);
		
		String msg = "";
		String loc = "/memo/memo.do";
		if(result > 0) {
			msg = "메모 등록 성공!!";
		} else {
			msg = "메모 등록 실패!!";
		}
		mv.addObject("msg",msg);
		mv.addObject("loc",loc);
		mv.setViewName("common/msg");
		
		return mv;
	}

	
}
