package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.CodeDetail;

public interface CodeDetailMapper {
	//등록처리
	public void create(CodeDetail codeDetail) throws Exception;
	
	public Integer getMaxSortSeq(String groupCode) throws Exception; 

	public List<CodeDetail> list() throws Exception;
}
