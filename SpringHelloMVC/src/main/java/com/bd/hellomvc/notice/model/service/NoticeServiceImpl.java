package com.bd.hellomvc.notice.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bd.hellomvc.notice.model.dao.NoticeDao;
import com.bd.hellomvc.notice.model.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public NoticeServiceImpl(NoticeDao dao, SqlSessionTemplate session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Notice> searchNoticeAll(int cPage, int numPerpage) {
		return dao.searchNoticeAll(session, cPage, numPerpage);
	}

	@Override
	public int selectNoticeCount() {
		return dao.selectNoticeCount(session);
	}

	@Override
	public Notice searchNoticeNo(int noticeNo) {
		return dao.searchNoticeNo(session, noticeNo);
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
