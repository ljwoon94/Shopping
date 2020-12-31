package com.shopping.service;

import java.util.List;

import com.shopping.domain.CodeGroup;

public interface CodeGroupService {
	//그룹코드생성
	public void register(CodeGroup codeGroup) throws Exception;
	//그룹코드목록
	public List<CodeGroup> list() throws Exception;
	//상세화면
	public CodeGroup read(String groupCode) throws Exception;
}
