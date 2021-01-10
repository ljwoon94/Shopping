package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Item;

public interface ItemMapper {
	//제품 등록
	public void create(Item item) throws Exception;
	//제품 목록
	public List<Item> list() throws Exception;
	
}
