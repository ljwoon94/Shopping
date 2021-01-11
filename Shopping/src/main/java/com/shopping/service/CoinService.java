package com.shopping.service;

import java.util.List;

import com.shopping.domain.ChargeCoin;

public interface CoinService {

	public void charge(ChargeCoin chargeCoin) throws Exception;

	public List<ChargeCoin> list(int userNo) throws Exception;
	
}
