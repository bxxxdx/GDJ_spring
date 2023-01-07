package com.bd.hellomvc.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bd.hellomvc.admin.model.service.AdminService;
import com.bd.hellomvc.member.model.vo.Member;

@Controller
public class AdminController {
	private AdminService service;
	
	@Autowired
	public AdminController(AdminService service) {
		this.service = service;
	}
	
	@RequestMapping("/admin/memberList.do")
	public ModelAndView memberList(ModelAndView mv, HttpServletRequest request,
							@RequestParam(value="cPage", defaultValue="1")int cPage,
							@RequestParam(value="numPerpage", defaultValue="10")int numPerpage) {
		
		List<Member> members = service.selectMemberList(cPage, numPerpage);
//		if(members!= null) {
//			members.stream().forEach(v->System.out.println(v));
//		} else {
//			System.out.println("망");
//		}
		
		int totalData = service.selectMemberCount();
		//System.out.println(totalData);
		String pageBar = "";
		//1. pageBar의 번호 갯수를 정한다.
		int pageBarSize = 5;
		//2. 총 페이지수 -> ceil 쓰면 나머지 발생시 무조건 올림
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		//3. 출력할 번호 세팅
		int pageNo = (cPage-1)/pageBarSize*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		//html코드 생성하기
		if(pageNo==1) {
			pageBar += "<span>[이전] </span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberList.do?cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+" </span>";
			} else { 
				pageBar += "<a href='"+request.getContextPath()+"/admin/memberList.do?cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span> [다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberList.do?cPage="+pageNo+"&numPerpage="+numPerpage+"'> [다음]</a>";
		}
		
		mv.addObject("members",members);
		mv.addObject("pageBar",pageBar);
		mv.setViewName("member/memberList");
		
		return mv;
	}
			
	@RequestMapping("/admin/searchMember")
	public ModelAndView searchMemberList(ModelAndView mv, HttpServletRequest request,
							@RequestParam(value="cPage", defaultValue="1")int cPage,
							@RequestParam(value="numPerpage", defaultValue="10")int numPerpage,
							@RequestParam(value="searchType") String type,
							@RequestParam(value="searchKeyword") String keyword) {
		
		Map param = new HashMap();
		param.put("type", type);
		param.put("keyword", keyword);
		
		List<Member> members = service.selectSearchMemberList(param, cPage, numPerpage);
//		if(members!= null) {
//			members.stream().forEach(v->System.out.println(v));
//		} else {
//			System.out.println("망");
//		}
		int totalData = service.selectSearchMemberCount(param);
//		System.out.println(totalData);
		
		String pageBar = "";
		//1. pageBar의 번호 갯수를 정한다.
		int pageBarSize = 5;
		//2. 총 페이지수 -> ceil 쓰면 나머지 발생시 무조건 올림
		int totalPage = (int)Math.ceil((double)totalData/numPerpage);
		//System.out.println("totalPage = " + totalPage);
		//3. 출력할 번호 세팅
		int pageNo = (cPage-1)/pageBarSize*pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		//html코드 생성하기
		if(pageNo==1) {
			pageBar += "<span>[이전] </span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/searchMember?searchType="+type+"&searchKeyword="+keyword+"&cPage="+(pageNo-1)+"&numPerpage="+numPerpage+"'>[이전] </a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar += "<span>"+pageNo+" </span>";
			} else { 
				pageBar += "<a href='"+request.getContextPath()+"/admin/searchMember?searchType="+type+"&searchKeyword="+keyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'>"+pageNo+" </a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span> [다음]</span>";
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/searchMember?searchType="+type+"&searchKeyword="+keyword+"&cPage="+pageNo+"&numPerpage="+numPerpage+"'> [다음]</a>";
		}
		
		mv.addObject("members", members);
		mv.addObject("pageBar", pageBar);
		mv.setViewName("member/searchMemberList");
		
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
