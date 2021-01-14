package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.domain.Pds;
import com.shopping.mapper.PdsMapper;

@Service
public class PdsServiceImpl implements PdsService {
	@Autowired
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
	
	//공개자료 등록
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
	
	//공개자료 목록
	@Override
	public List<Pds> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}
	
	//공개자료 상세화면
	@Override
	public Pds read(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		//공개자료 조회 건수 업데이트
		mapper.updateViewCnt(itemId);
		
		return mapper.read(itemId);
	}
	
	//공개자료 수정
	@Override
	public void modify(Pds item) throws Exception {
		// TODO Auto-generated method stub
		mapper.update(item);
		
		//첨부파일 수정
		Integer itemId = item.getItemId();
		mapper.deleteAttach(itemId);
		String[] files = item.getFiles();
		if(files == null) {
			return;
		}
		for(String fileName : files) {
			mapper.replaceAttach(fileName,itemId);
		}
		
	}
	
	//공개자료 삭제
	@Override
	public void remove(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		//첨부파일 삭제
		mapper.deleteAttach(itemId);
		mapper.delete(itemId);
	}
}
