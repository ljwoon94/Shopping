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
	
	@Override
	public void register(CodeGroup codeGroup) throws Exception{
		mapper.create(codeGroup);
	}
	
	@Override
	public List<CodeGroup> list() throws Exception{
		return mapper.list();
	}
	
	@Override
	public CodeGroup read(String groupCode) throws Exception {
		
		return mapper.read(groupCode);
	}
	
}
