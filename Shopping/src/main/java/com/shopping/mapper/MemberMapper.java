package com.shopping.mapper;

import com.shopping.domain.MemberAuth;

public interface MemberMapper {
	//회원권한 생성
	public void createAuth(MemberAuth memberAuth) throws Exception;
	//회원권한 삭제
	public void deleteAuth(int userNo) throws Exception;
	
}
