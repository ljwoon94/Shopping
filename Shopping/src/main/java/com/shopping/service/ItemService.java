package com.shopping.service;

import java.util.List;

import com.shopping.domain.Item;

public interface ItemService {
	//상품등록
	public void register(Item item) throws Exception;
	//상품목록
	public List<Item> list() throws Exception;
	//상품 상세보기
	public Item read(Integer itemId) throws Exception;
	//상품 수정
	public void modify(Item item) throws Exception;
	//상품 삭제
	public void remove(Integer itemId) throws Exception;
	//원본 이미지 표시
	public String getPicture(Integer itemId) throws Exception;
	//미리보기 이미지 표시
	public String getPreview(Integer itemId) throws Exception;

}
