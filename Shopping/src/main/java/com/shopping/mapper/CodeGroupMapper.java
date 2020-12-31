package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.CodeGroup;

public interface CodeGroupMapper {
	//코드그룹 생성
	public void create(CodeGroup codeGroup) throws Exception;
	//코드그룹 목록
	public List<CodeGroup> list() throws Exception;
	//상세화면
	public CodeGroup read(String groupCode) throws Exception;
}
