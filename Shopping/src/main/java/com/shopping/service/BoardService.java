package com.shopping.service;

import java.util.List;

import com.shopping.domain.Board;

public interface BoardService {
	//게시판 등록 처리
	public void register(Board board) throws Exception;
	//게시판 목록
	public List<Board> list() throws Exception;

}
