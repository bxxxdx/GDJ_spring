package com.bd.hellomvc.notice.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bd.hellomvc.notice.model.vo.Notice;

public interface NoticeDao {
	
	List<Notice> searchNoticeAll(SqlSessionTemplate session, int cPage, int numPerpage);
	
	int selectNoticeCount(SqlSessionTemplate session);
	
	Notice searchNoticeNo(SqlSessionTemplate session, int noticeNo);
}
