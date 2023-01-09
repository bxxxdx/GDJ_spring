package com.bs.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoDao {

	List<Memo> selectMemoList(SqlSessionTemplate session);
	
	int insertMemo(SqlSessionTemplate session, Memo m);
	
}
