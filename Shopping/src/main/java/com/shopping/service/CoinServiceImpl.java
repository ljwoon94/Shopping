package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.domain.ChargeCoin;
import com.shopping.mapper.CoinMapper;

public class CoinServiceImpl implements CoinService {
	@Autowired
	private CoinMapper mapper;
	
	@Transactional
	@Override
	public void charge(ChargeCoin chargeCoin) throws Exception {
		// TODO Auto-generated method stub
		mapper.charge(chargeCoin);
		//코인 충전 내역 등록
		mapper.create(chargeCoin);
	}
	
	@Override
	public List<ChargeCoin> list(int userNo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.list(userNo);
	}
}
