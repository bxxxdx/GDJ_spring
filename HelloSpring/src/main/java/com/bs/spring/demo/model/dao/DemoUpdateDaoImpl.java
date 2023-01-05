package com.bs.spring.demo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.vo.Demo;

@Repository
public class DemoUpdateDaoImpl implements DemoUpdateDao {

	@Override
	public Demo selectDemoDevNo(SqlSessionTemplate session, int devNo) {
		return session.selectOne("demoupdate.selectDemoDevNo", devNo);
	}

	@Override
	public int updateDemo(SqlSessionTemplate session, Demo d) {
		return session.update("demoupdate.updateDemo", d);
	}

}
