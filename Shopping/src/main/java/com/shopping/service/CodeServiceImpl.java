package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.CodeLabelValue;
import com.shopping.mapper.CodeMapper;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeMapper mapper;
	
	@Override
	public List<CodeLabelValue> getCodeGroupList() throws Exception {
		// TODO Auto-generated method stub
		return mapper.getCodeGroupList();
	}
	//지정된 그룹코드에 해당하는 코드 목록 조회
	@Override
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getCodeList(groupCode);
	}

}
