package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.Item;
import com.shopping.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper mapper;
	//상품등록
	@Override
	public void register(Item item) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(item);
	}
	
	//상품목록
	@Override
	public List<Item> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}
	
	//상품 상세보기
	@Override
	public Item read(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(itemId);
	}
	
	//상품 수정
	@Override
	public void modify(Item item) throws Exception {
		// TODO Auto-generated method stub
		mapper.update(item);
	}
	
	//상품 삭제
	@Override
	public void remove(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		mapper.delete(itemId);
	}
	
	//원본이미지 표시
	@Override
	public String getPicture(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getPicture(itemId);
	}
	
	//미리보기 이미지 표시
	@Override
	public String getPreview(Integer itemId) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getPreview(itemId);
	}
}
