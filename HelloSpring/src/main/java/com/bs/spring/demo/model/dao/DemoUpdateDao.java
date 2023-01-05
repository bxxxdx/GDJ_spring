package com.bs.spring.demo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.demo.model.vo.Demo;

public interface DemoUpdateDao {
	Demo selectDemoDevNo(SqlSessionTemplate session, int devNo);
	int updateDemo(SqlSessionTemplate session, Demo d);
}
