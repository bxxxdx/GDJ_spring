package com.bd.hellomvc.admin.model.service;

import java.util.List;
import java.util.Map;

import com.bd.hellomvc.member.model.vo.Member;

public interface AdminService {
	List<Member> selectMemberList(int cPage, int numPerpage);
	
	int selectMemberCount();
	
	List<Member> selectSearchMemberList(Map param, int cPage, int numPerpage);
	
	int selectSearchMemberCount(Map param);
	
	
	
	
	
	
}
