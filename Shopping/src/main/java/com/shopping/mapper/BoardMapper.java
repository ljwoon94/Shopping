package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Board;

public interface BoardMapper {

	public void create(Board board) throws Exception;

	public List<Board> list() throws Exception;

}
