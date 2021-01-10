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
	
	@Override
	public void register(Item item) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(item);
	}
	
	@Override
	public List<Item> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}
}
