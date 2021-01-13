package com.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.domain.Pds;
import com.shopping.mapper.PdsMapper;

@Service
public class PdsServiceImpl implements PdsService {
	private PdsMapper mapper;
	
	//첨부파일 목록
	@Override
	public List<String> getAttach(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getAttach(itemId);
	}
	
	//첨부파일 다운로드 건수
	@Override
	public void updateAttachDownCnt(String fullName) throws Exception {
		// TODO Auto-generated method stub
		mapper.updateAttachDownCnt(fullName);
	}
	
	//첨부파일 등록
	@Transactional
	@Override
	public void register(Pds item) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(item);
		
		//첨부파일 추가
		String[] files = item.getFiles();
		if(files == null) {
			return;
		}
		
		for(String fileName : files) {
			mapper.addAttach(fileName);
		}
	}
}
