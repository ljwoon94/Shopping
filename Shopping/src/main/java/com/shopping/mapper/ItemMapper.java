package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Item;

public interface ItemMapper {
	//제품 등록
	public void create(Item item) throws Exception;
	//제품 목록
	public List<Item> list() throws Exception;
	//제품 상세보기
	public Item read(Integer itemId) throws Exception;
	//제품 수정
	public void update(Item item) throws Exception;
	//제품 삭제
	public void delete(Integer itemId) throws Exception;
	//원본 이미지 표시
	public String getPicture(Integer itemId) throws Exception;
	//미리보기 이미지 표시
	public String getPreview(Integer itemId) throws Exception;
}
