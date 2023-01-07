package com.bd.hellomvc.notice.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bd.hellomvc.notice.model.service.NoticeService;
import com.bd.hellomvc.notice.model.vo.Notice;

@Controller
public class NoticeController {
	
	private NoticeService service;
	
	@Autowired
	public NoticeController(NoticeService service) {
		this.service = service;
	}
	
	@RequestMapping("/notice/noticeList.do")
	public ModelAndView noticeList(ModelAndView mv, HttpServletRequest request,
				@RequestParam(value="cPage", defaultValue="1")int cPage,
				@RequestParam(value="numPerpage", defaultValue="10")int numPerpage) {
		
		List<Notice> notices = service.searchNoticeAll(cPage, numPerpage);
		
		int totalData = service.selectNoticeCount();
		
		String pageBar = "";
		int pageBarSize = 5;
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		
		int pageNo = (cPage-1)/pageBarSize*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		if(pageNo==1) {
			pageBar += "<span>[이전] </span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/notice/noticeList.do?cPage="+(pageNo-1)+"'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+" </span>";
			} else { 
				pageBar += "<a href='"+request.getContextPath()+"/notice/noticeList.do?cPage="+pageNo+"'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span> [다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/notice/noticeList.do?cPage="+pageNo+"'> [다음]</a>";
		}
		
		mv.addObject("notices", notices);
		mv.addObject("pageBar", pageBar);
		mv.setViewName("notice/noticeList");
		
		return mv;
	}
	
	@RequestMapping("/notice/readNotice.do")
	public ModelAndView readNotice(ModelAndView mv, int noticeNo) {
		
		Notice n = service.searchNoticeNo(noticeNo);
		
		mv.addObject("notice",n);
		mv.setViewName("notice/readNotice");
		
		return mv;
	}
	
	@RequestMapping("/notice/writeNotice.do")
	public String writeNotice() {
		return "notice/writeNotice";
	}
	
	@RequestMapping("/notice/writeNoticeEnd.do")
	public ModelAndView writeNoticeEnd(ModelAndView mv, 
				@RequestParam Map param){
		
		//System.out.println(param);
		int result = service.insertNotice(param);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","공지사항 등록 성공!!");
			mv.addObject("loc","/notice/noticeList.do");
		} else {
			mv.addObject("msg","공지사항 등록 실패!!");
			mv.addObject("loc","/notice/writeNotice.do");
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/notice/updateNotice.do")
	public ModelAndView updateNotice(ModelAndView mv, int noticeNo) {
		
		Notice n = service.searchNoticeNo(noticeNo);
		
		mv.addObject("notice", n);
		mv.setViewName("notice/updateNotice");
		
		return mv;
	}
	
	@RequestMapping("/notice/updateNoticeEnd.do")
	public ModelAndView updateNoticeEnd(ModelAndView mv, @RequestParam Map param) {
		//System.out.println(param);
		
		int result = service.updateNotice(param);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","공지사항 수정 성공!!");
			mv.addObject("loc","/notice/noticeList.do");
		} else {
			mv.addObject("msg","공지사항 수정 실패!!");
			mv.addObject("loc","/notice/updateNotice.do?noticeNo="+param.get("noticeNo"));
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	@RequestMapping("/notice/deleteNotice.do")
	public ModelAndView deleteNotice(ModelAndView mv, int noticeNo) {
		// 파일삭제부분 일단 생략 2023-01-07
		
		int result = service.deleteNotice(noticeNo);
		
		String msg = "";
		String loc = "";
		if(result > 0) {
			mv.addObject("msg","공지사항 삭제 성공!!");
			mv.addObject("loc","/notice/noticeList.do");
		} else {
			mv.addObject("msg","공지사항 삭제 실패!!");
			mv.addObject("loc","/notice/readNotice.do?noticeNo="+noticeNo);
		}
		mv.setViewName("common/msg");
		
		return mv;
	}
	
	
	
}
