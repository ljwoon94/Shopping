package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Member;
import com.shopping.domain.MemberAuth;

public interface MemberMapper {
	//회원권한 생성
	public void createAuth(MemberAuth memberAuth) throws Exception;
	//회원권한 삭제
	public void deleteAuth(int userNo) throws Exception;
	//회원등록 처리
	public void create(Member member) throws Exception;
	//상세화면
	public Member read(int userNo) throws Exception;
	//회원수정 처리
	public void update(Member member) throws Exception;
	//삭제처리
	public void delete(int userNo) throws Exception;
	//목록화면
	public List<Member> list() throws Exception;
	
}
