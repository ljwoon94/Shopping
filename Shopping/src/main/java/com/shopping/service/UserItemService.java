package com.shopping.service;

import java.util.List;

import com.shopping.domain.Item;
import com.shopping.domain.Member;
import com.shopping.domain.UserItem;

public interface UserItemService {

	public void register(Member member, Item item) throws Exception;

	public List<UserItem> list(int userNo) throws Exception;

	public UserItem read(int userItemNo) throws Exception;



}
