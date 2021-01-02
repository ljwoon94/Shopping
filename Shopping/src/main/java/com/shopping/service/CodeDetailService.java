package com.shopping.service;

import java.util.List;

import com.shopping.domain.CodeDetail;

public interface CodeDetailService {
	//코드 등록
	public void register(CodeDetail codeDetail) throws Exception;
	//코드 목록
	public List<CodeDetail> list() throws Exception;
	//상세화면
	public CodeDetail read(CodeDetail codeDetail) throws Exception;
	//코드 수정
	public void modify(CodeDetail codeDetail) throws Exception;
	//코드 삭제
	public void remove(CodeDetail codeDetail) throws Exception; 
}
