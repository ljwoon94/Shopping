package com.shopping.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.CodeGroup;
import com.shopping.mapper.CodeGroupMapper;

@Service
public class CodeGroupServiceImpl implements CodeGroupService {

	@Autowired
	private CodeGroupMapper mapper;
	
	//코드그룹 등록
	@Override
	public void register(CodeGroup codeGroup) throws Exception{
		mapper.create(codeGroup);
	}
	
	//코드그룹 목록
	@Override
	public List<CodeGroup> list() throws Exception{
		return mapper.list();
	}
	
	//코드그룹 상세화면
	@Override
	public CodeGroup read(String groupCode) throws Exception {
		
		return mapper.read(groupCode);
	}
	
	//코드그룹 수정
	@Override
	public void modify(CodeGroup codeGroup) throws Exception {
		// TODO Auto-generated method stub
		mapper.update(codeGroup);
	}
	
	//코드그룹 삭제
	@Override
	public void remove(String groupCode) throws Exception {
		// TODO Auto-generated method stub
		mapper.delete(groupCode);
	}
	
	
	
}
