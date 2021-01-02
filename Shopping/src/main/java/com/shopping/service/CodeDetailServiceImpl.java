package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.CodeDetail;
import com.shopping.mapper.CodeDetailMapper;

@Service
public class CodeDetailServiceImpl implements CodeDetailService {
	@Autowired
	private CodeDetailMapper mapper;
	
	@Override
	public void register(CodeDetail codeDetail) throws Exception {
		// TODO Auto-generated method stub
		String groupCode=codeDetail.getGroupCode();
		int maxSortSeq = mapper.getMaxSortSeq(groupCode);
		
		codeDetail.setSortSeq(maxSortSeq + 1);
		mapper.create(codeDetail);
		
	}
	
	@Override
	public List<CodeDetail> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}
	
	@Override
	public CodeDetail read(CodeDetail codeDetail) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(codeDetail);
	}
	@Override
	public void modify(CodeDetail codeDetail) throws Exception {
		// TODO Auto-generated method stub
	
		mapper.update(codeDetail);
	}
}
