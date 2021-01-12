package com.shopping.service;

import java.util.List;

import com.shopping.domain.ChargeCoin;
import com.shopping.domain.PayCoin;

public interface CoinService {

	public void charge(ChargeCoin chargeCoin) throws Exception;

	public List<ChargeCoin> list(int userNo) throws Exception;
	//사용자의 상품 구매 내역을 반환
	public List<PayCoin> listPayHistory(int userNo) throws Exception;
	
}
