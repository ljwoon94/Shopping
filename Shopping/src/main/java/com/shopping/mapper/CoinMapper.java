package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.ChargeCoin;

public interface CoinMapper {
	//코인 충전 내역 등록
	public void create(ChargeCoin chargeCoin) throws Exception;
	//코인 충전
	public void charge(ChargeCoin chargeCoin) throws Exception;
	//코인 충전 내역
	public List<ChargeCoin> list(int userNo) throws Exception;
}
