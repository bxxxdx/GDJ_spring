package com.bd.hellomvc.notice.model.service;

import java.util.List;

import com.bd.hellomvc.notice.model.vo.Notice;

public interface NoticeService {
	
	List<Notice> searchNoticeAll(int cPage, int numPerpage);

	int selectNoticeCount();
	
	Notice searchNoticeNo(int noticeNo);
	
	
	
	
	
}