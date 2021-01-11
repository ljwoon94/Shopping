package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.ChargeCoin;
import com.shopping.domain.PayCoin;

public interface CoinMapper {
	//코인 충전 내역 등록
	public void create(ChargeCoin chargeCoin) throws Exception;
	//코인 충전
	public void charge(ChargeCoin chargeCoin) throws Exception;
	//코인 충전 내역
	public List<ChargeCoin> list(int userNo) throws Exception;
	//코인 지급
	public void pay(PayCoin payCoin) throws Exception;
	//구매 내역 등록
	public void createPayHistory(PayCoin payCoin) throws Exception;
}
