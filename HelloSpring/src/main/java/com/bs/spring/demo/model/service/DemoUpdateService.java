package com.bs.spring.demo.model.service;

import com.bs.spring.demo.model.vo.Demo;

public interface DemoUpdateService {
	
	Demo selectDemoDevNo(int devNo);
	
	int updateDemo(Demo d);
	
}
