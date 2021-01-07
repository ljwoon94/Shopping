package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.Board;
import com.shopping.mapper.BoardMapper;

@Service
public class BoardServiceImple implements BoardService {
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public void register(Board board) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(board);
	}
	
}
