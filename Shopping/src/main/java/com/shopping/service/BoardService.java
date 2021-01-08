package com.shopping.service;

import java.util.List;

import com.shopping.domain.Board;
import com.shopping.domain.PageRequest;

public interface BoardService {
	//게시판 등록 처리
	public void register(Board board) throws Exception;
	//게시판 목록
	public List<Board> list(PageRequest pageRequest) throws Exception;
	//게시판 상세화면
	public Board read(Integer boardNo) throws Exception;
	//게시판 수정
	public void modify(Board board) throws Exception;
	//게시글 삭제
	public void remove(Integer boardNo) throws Exception;
	//게시글 전체 건수 조회
	public int count() throws Exception;
	
}
