package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.UserItem;

public interface UserItemMapper {

	public void create(UserItem userItem) throws Exception;

	public List<UserItem> list(int userNo) throws Exception;

	public UserItem read(int userItemNo) throws Exception;

}
