package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.service.DemoService;
import com.bs.spring.demo.model.vo.Demo;

@Controller
public class DemoController {
	
	//setter 이용
	@Autowired
	private DemoService service;
	
	//생성자 이용
	public DemoController(DemoService service) {
		this.service = service;
	}
	
	//클라이언트가 요청한 서비스를 실행해주는 기능
	//클라이언트가 요청한 서비스 주소(URL)에 맞는 메소드를 구현
	//메소드 구현할 때 서비스주소와 연결해주는 어노테이션을 선언
	//@RequestMapping(연결주소[, 추가옵션 설정])  
	// - Rest 방식의 서비스 구현시 사용 -> @GetMapping, @PostMapping.. 어노테이션 사용
	
	//demo페이지를 연결하는 메소드 구현하기
	//반환형은 String -> SpringBean으로 등록된 ViewResolver를 통해 페이지를 지정된 위치에서 찾아 응답한다.
	//매개변수는 없음
	//메소드 명은 알아서 해!! -> 통상 주소값과 비슷한 명칭으로 한다.
	@RequestMapping("/demo/demo.do") //서블릿 배울때 Mapping 주소와 같은 느낌
	public String demoPage() {
		//InternalResourceViewResolver
		//InternalResourceViewResolver에 저장된 prefix, suffix의 내용을 반환값이랑 연결해서 view 화면을 찾음
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		
		
		return "demo/demo";
		// InternalResourceViewResolver으로 parsing된 반환값의 주소
		// /WEB-INF/views/demo/demo.jsp -> RequestDispatcher.forward()를 실행함
		// request.getRequestDispatcher("/WEB-INF/views/demo/demo.jsp").forward(request, response); 랑 같다.
	}
	
	
	// 요청 매핑 메소드의 매개변수와 반환형
	// 1. 반환형 ( * 주로 String 과 ModelAndView 객체를 사용할 예정)
	//	- String : ViewResolver에 의해서 view화면을 반환할 때 사용 
	//	- void : HttpResponse로 직접 응답 메세지를 작성할 때 사용(upload, download... )
	//	- ModelAndView : ModelAndView(Spring에서 제공하는 객체)객체를 반환(화면정보, view에 전송할 데이터를 가지고 있음)
	//	- 클래스타입(ReferenceType) : 생성된 객체를 반환 -> json 방식으로 반환. ( * 이번엔 jackson을 사용할 예정)
	
	// 2. 매개변수 -> Spring이 알아서 넣어줌
	//	- HttpServletRequest : 서블릿의 그녀석!!
	//	- HttpServletResponse : 서블릿의 응답할때 쓰던 그녀석!!
	//	- HttpSession : 서블릿의 그녀석!!
	//	- java.util.Locale : 운영되고 있는 서버의 지역정보를 가져오는 객체
	//	- InputStream/Reader : 파일 입출력할때 사용하는 stream객체
	//	- OuputStream/Writer : 파일 입출력할때 사용하는 stream객체
	//	- 기본 자료형 변수 : 클라이언트가 보낸 name 명칭과 변수명이 일치하면 대입해줌..!! -> request.getParameter를 안써도 된다..!!!
	//		- name과 일치하지 않는 변수명을 사용했을 때는 @RequestParam 어노테이션을 이용해서 mapping할 수 있음
	// 		- 추가적인 설정이 필요할 때도 @RequestParam 어노테이션을 사용할 수 있음.
	//		* 주의 ! : 기본 자료형 변수를 선언했을 때는 반드시 모든 변수에 연결되는 값을 전달해야함.
	// 	- 클래스(ReferenceType) 변수 : command라고 하며, 클라이언트가 보낸 데이터를 지정한 타입의 클래스를 생성해서 대입해 줌
	//		- 조건 : 필드 명과 클라이언트가 보낸 name 명이 일치하는 값
	//	- java.util.Map : 클라이언트가 보낸 데이터를 map으로 대입해 줌.
	//	- Model : 서버에서 데이터를 저장하는 용도의 1회용 데이터 저장용 객체, 생명주기가 request와 동일하다. but request보다 더 가볍다.
	//	- ModelAndView : 저장할 데이터, 화면 정보를 한번에 저장하는 객체
	
	//	- 기본 자료형 변수 선언시 @RequestHeader, @CookieValue 어노테이션을 이용하면 header나 cookie의 값을 바로 저장할 수 있음.
	
	//	추가 어노테이션
	//	@ResponseBody -> json 형태로 반환할 때 사용, 메소드 선언부에 사용해주면 된다.
	//	@RequestBody -> json형태로 전달된 객체를 vo객체와 mapping 해주는 annotaion
	
	// 서블릿처럼 이용하기 
	@RequestMapping("/demo/demo1.do")
	//public String demo1(HttpServletRequest req, HttpServletResponse res) {
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println(req);
		System.out.println(res);
		
		res.setCharacterEncoding("utf-8");
		String devName = req.getParameter("devName");
		int devAge = Integer.parseInt(req.getParameter("devAge"));
		String devEmail = req.getParameter("devEmail");
		String devGender = req.getParameter("devGender");
		String[] devLang = req.getParameterValues("devLang");
		
		System.out.println(devName +" / "+  devAge +" / "+  devEmail +" / "+ devGender);
		for(String s : devLang){
			System.out.print(s + " / ");
		}
		
		Demo d = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
		
		req.setAttribute("demo", d);
		
		
		//return "/demo/demoResult";
		//req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		out.print("<h1>데이터</h1>");
	}
	
	
	//기본자료형으로 데이터 받아오기
	//변수명이 다르면 그냥 null처리, 값이 입력이 안되면 400Error
	@RequestMapping("/demo/demo2.do")
	public String basicType(String devName, int devAge, String devGender, String devEmail, String[] devLang, Model model) {
		
		System.out.println(devName + " " + devAge + " " + devGender + " " + devEmail);
		for(String l : devLang) {
			System.out.println(l);
		}
		
		Demo d = Demo.builder().devName(devName).devAge(devAge).devGender(devGender).devEmail(devEmail).devLang(devLang).build();
		
		//model 객체이 데이터 저장하기
		//key, value형식으로 데이터를 저장함
		//addAttribute() 메소드 이용	
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
	
	
	@RequestMapping("/demo/demo3.do")
	public String requestParamTest(@RequestParam(value="devName")String name, 
									@RequestParam(value="devAge", defaultValue = "1")int age, 
									@RequestParam(value="devGender")String email, 
									@RequestParam(value="devEmail")String gender, 
									@RequestParam(value="devLang", required = false)String[] devLang, Model model) {
		
		System.out.println(name + " " + age + " " + email + " " + gender);
		if(devLang != null) {
			for(String l : devLang) {
				System.out.println(l);
			}
		}
		
		
		Demo d = Demo.builder().devName(name).devAge(age).devGender(email).devEmail(gender).devLang(devLang).build();
		
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
		
	@RequestMapping("/demo/demo4.do")
	public String commandMappingTest(Demo demo, Model model) {
		System.out.println(demo);
		
		//조건 : 파라미터 name과 vo의 필드명이 일치해야 함
		//일반 자료형만 받을 수 있다. 배열을 포함한
		model.addAttribute("demo", demo);
		
		return "demo/demoResult";
	}
	
	@RequestMapping("/demo/demo5.do")
	public String mapMappingTest(@RequestParam Map param,String[] devLang, Model model) {
		
		param.put("devLang",devLang);
		System.out.println(param);
		if(devLang != null) {
			for(String d : devLang) {
				System.out.println(d);
			}
		}
		model.addAttribute("demo",param);
		
		return "demo/demoResult";
	}
	
	
	@RequestMapping("/demo/demo6.do")
	//@CookieValue(key값) 을 쓰면 알아서 쿠키값을 가져와준다.
	public String extraTest(@CookieValue(value="testData", required = false) String testData,
							@RequestHeader(value="User-agent") String userAgent,
							@SessionAttribute (value="sessionId") String id,
							@RequestHeader(value="Referer") String referer) {
		System.out.println(testData);
		
		System.out.println(userAgent);
		
		System.out.println(id);
		
		System.out.println(referer);
		
		return "demo/demoResult";
	}
	
	
	//ModelAndView 로 반환하기
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelAndViewTest(ModelAndView mv, Demo demo) {
		
		//ModelAndView 객체는 view 설정과, 데이터 저장을 같이 할 수 있는 객체
		//data저장 : addObject("key",value) 메소드 이용
		//view설정 : setViewName("view이름");
		mv.addObject("demo", demo);
		mv.setViewName("demo/demoResult");
		
		return mv;
	}
	
	
	//restful하게 서비스를 구현할 때 사용 -> @Controller를 @RestController로 쓴다.
//	@GetMapping
//	@PostMapping
//	@PutMapping
//	@DeleteMapping
//	@PathVariable

	@RequestMapping("/demo/responsetest.do")
	@ResponseBody
	public List<String> responseTest() {
		
		return List.of("1","2","3","4");	
	}
	
	
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Demo demo) {
		int result = service.insertDemo(demo);
		//spring에서 redirect 처리하기
		return "redirect:/demo/demo.do";
	}
	
//	@RequestMapping("/demo/selectDemoList.do")
//	public List<Demo> selectDemoList(){
//		List<Demo> demos = service.selectDemoList();
//		
//		if(demos!=null) {
//			demos.stream().forEach(v->System.out.println(v));
//		}else {
//			System.out.println("망");
//		}
//		
//		return demos;
//	}
	
	@RequestMapping("/demo/selectDemoList.do")
	public ModelAndView selectDemoList(ModelAndView mv){
		List<Demo> demos = service.selectDemoList();
		
		if(demos!=null) {
			demos.stream().forEach(v->System.out.println(v));
		}else {
			System.out.println("망");
		}
		
		mv.addObject("demos", demos);
		mv.setViewName("demo/demolist");
		
		return mv;
	}
	
	
	
	
	
	
	
	
	
}
