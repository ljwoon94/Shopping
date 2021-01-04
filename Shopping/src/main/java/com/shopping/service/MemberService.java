package com.shopping.service;

import java.util.List;

import com.shopping.domain.Member;

public interface MemberService {
	//등록처리
	public void register(Member member) throws Exception;
	//상세화면
	public Member read(int userNo) throws Exception;
	//수정처리
	public void modify(Member member) throws Exception;
	//삭제처리
	public void remove(int userNo) throws Exception;
	//목록화면
	public List<Member> list() throws Exception;
}
