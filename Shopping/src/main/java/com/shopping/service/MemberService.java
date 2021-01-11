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
	//회원테이블의 데이터 건수 반환
	public int countAll() throws Exception;
	//최초 관리자 생성을 위한 데이터를 등록한다.
	public void setupAdmin(Member member) throws Exception;
	
	public int getCoin(int userNo) throws Exception;
	
}
