package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.CodeDetail;

public interface CodeDetailMapper {
	//등록처리
	public void create(CodeDetail codeDetail) throws Exception;
	
	public Integer getMaxSortSeq(String groupCode) throws Exception; 
	//목록 처리
	public List<CodeDetail> list() throws Exception;
	//상세화면
	public CodeDetail read(CodeDetail codeDetail) throws Exception;
	//수정 처리
	public void update(CodeDetail codeDetail) throws Exception;
	//삭제 처리
	public void delete(CodeDetail codeDetail) throws Exception;
	
}
