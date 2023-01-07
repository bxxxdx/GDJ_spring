package com.bd.hellomvc.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bd.hellomvc.notice.model.vo.Notice;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Override
	public List<Notice> searchNoticeAll(SqlSessionTemplate session, int cPage, int numPerpage) {
		return session.selectList("notice.searchNoticeAll", null, new RowBounds((cPage-1)*numPerpage, numPerpage));
	}

	@Override
	public int selectNoticeCount(SqlSessionTemplate session) {
		return session.selectOne("notice.selectNoticeCount");
	}

	@Override
	public Notice searchNoticeNo(SqlSessionTemplate session, int noticeNo) {
		return session.selectOne("notice.searchNoticeNo", noticeNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}