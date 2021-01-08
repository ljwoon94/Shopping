package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.Board;
import com.shopping.mapper.BoardMapper;

@Service
public class BoardServiceImple implements BoardService {
	@Autowired
	private BoardMapper mapper;
	
	//게시글 작성
	@Override
	public void register(Board board) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(board);
	}
	
	//게시글 목록
	@Override
	public List<Board> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}
	
	//게시글 상세보기
	@Override
	public Board read(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(boardNo);
	}
	
	//게시글 수정
	@Override
	public void modify(Board board) throws Exception {
		// TODO Auto-generated method stub
		mapper.update(board);
	}
	
	//게시글 삭제
	@Override
	public void remove(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		mapper.delete(boardNo);
		
	}
}
