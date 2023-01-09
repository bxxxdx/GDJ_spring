package com.bs.spring.memo.model.service;

import java.util.List;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoService {
	
	List<Memo> selectMemoList();
	
	int insertMemo(Memo m);
	
}
