package com.shopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shopping.domain.Pds;

public interface PdsMapper {
	//첨부파일 첨부
	public void addAttach(String fullName) throws Exception;
	//첨부파일 삭제
	public void deleteAttach(Integer itemId) throws Exception;
	//첨부파일 교체
	public void replaceAttach(@Param("fullName") String fullName,@Param("itemId") Integer itemId) throws Exception;
	//첨부일 목록
	public List<String> getAttach(Integer itemId) throws Exception;
	//첨부파일 다운로드 수 업데이트
	public void updateAttachDownCnt(String fullName) throws Exception;
	//공개자료 조회 건수 업데이트
	public void updateViewCnt(Integer itemId) throws Exception;
	//첨부파일 등록
	public void create(Pds item) throws Exception;
	
}
