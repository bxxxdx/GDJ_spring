package com.bs.spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoUpdateService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoUpdateController {

	private DemoUpdateService service;
	
	@Autowired
	public DemoUpdateController(DemoUpdateService service) {
		this.service = service;
	}
	
	
	@RequestMapping("/demo/demoUpdate.do")
	public ModelAndView demoUpdatePage(ModelAndView mv, int devNo) {
		Demo d = service.selectDemoDevNo(devNo);
		
		mv.addObject("demo",d);
		mv.setViewName("demo/updateDemo");
		
		System.out.println(d);
		
		return mv;
	}
	
	@RequestMapping("/demo/demoUpdateEnd.do")
	public String updateDemo(Demo d) {
		//System.out.println(d);
		
		int result = service.updateDemo(d);
		return "redirect:/demo/selectDemoList.do";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
