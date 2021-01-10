package com.shopping.service;

import java.util.List;

import com.shopping.domain.Item;

public interface ItemService {

	public void register(Item item) throws Exception;

	public List<Item> list() throws Exception;

}
