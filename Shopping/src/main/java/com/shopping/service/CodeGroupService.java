package com.shopping.service;

import java.util.List;

import com.shopping.domain.CodeGroup;

public interface CodeGroupService {
	//그룹코드 생성
	public void register(CodeGroup codeGroup) throws Exception;
	//그룹코드 목록
	public List<CodeGroup> list() throws Exception;
	//상세화면
	public CodeGroup read(String groupCode) throws Exception;
	//그룹코드 수정
	public void modify(CodeGroup codeGroup) throws Exception;
	//그룹코드 삭제
	public void remove(String groupCode) throws Exception;
	
}
