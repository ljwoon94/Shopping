package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Board;

public interface BoardMapper {
	//게시판 등록 처리
	public void create(Board board) throws Exception;
	//게시판 목록
	public List<Board> list() throws Exception;

}
