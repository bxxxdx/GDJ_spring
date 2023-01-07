package com.bd.hellomvc.notice.controller;

import java.util.List;

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
	public ModelAndView writeNoticeEnd(ModelAndView mv){
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
}
