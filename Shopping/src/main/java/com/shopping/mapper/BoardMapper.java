package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.Board;
import com.shopping.domain.PageRequest;

public interface BoardMapper {
	//게시판 등록 처리
	public void create(Board board) throws Exception;
	//게시판 목록
	public List<Board> list(PageRequest pageRequest) throws Exception;
	//게시글 상세화면
	public Board read(Integer boardNo) throws Exception;
	//게시글 수정
	public void update(Board board) throws Exception;
	//게시글 삭제
	public void delete(Integer boardNo) throws Exception;
	//게시글 전체 수 조회
	public int count() throws Exception;
	
}
