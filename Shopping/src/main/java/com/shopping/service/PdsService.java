package com.shopping.service;

import java.util.List;

import com.shopping.domain.Pds;

public interface PdsService {
	//첨부파일 목록
	public List<String> getAttach(Integer itemId) throws Exception;
	//첨부파일 다운로드 건수 업데이트
	public void updateAttachDownCnt(String fullName) throws Exception;
	//첨부파일 등록
	public void register(Pds item) throws Exception;
	
	
}
