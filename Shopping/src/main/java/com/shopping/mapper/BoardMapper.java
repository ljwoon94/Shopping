package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Board;

public interface BoardMapper {
	//게시판 등록 처리
	public void create(Board board) throws Exception;
	//게시판 목록
	public List<Board> list() throws Exception;
	//게시글 상세화면
	public Board read(Integer boardNo) throws Exception;
	//게시글 수정
	public void update(Board board) throws Exception;
	//게시글 삭제
	public void delete(Integer boardNo) throws Exception;
	
}
