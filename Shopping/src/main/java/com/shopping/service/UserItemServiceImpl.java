package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.domain.Item;
import com.shopping.domain.Member;
import com.shopping.domain.PayCoin;
import com.shopping.domain.UserItem;
import com.shopping.exception.NotEnoughCoinException;
import com.shopping.mapper.CoinMapper;
import com.shopping.mapper.UserItemMapper;

@Service
public class UserItemServiceImpl implements UserItemService {
	
	@Autowired
	private UserItemMapper mapper;
	
	@Autowired
	private CoinMapper coinMapper;
	
	@Transactional
	@Override
	public void register(Member member, Item item) throws Exception {
		// TODO Auto-generated method stub
		int userNo = member.getUserNo();
		//회원이 가진 코인을 가져온다
		int coin = member.getCoin();
		int itemId = item.getItemId();
		int price = item.getPrice();
		
		UserItem userItem = new UserItem();
		userItem.setUserNo(userNo);
		userItem.setItemId(itemId);
		
		//사용자의 코인이 부족한지를 체크한다.
		if(coin < price) {
			throw new NotEnoughCoinException("There is Not Enough Coin.");
		}
		
		PayCoin payCoin = new PayCoin();
		payCoin.setUserNo(userNo);
		payCoin.setItemId(itemId);
		payCoin.setAmount(price);
		
		coinMapper.pay(payCoin);
		coinMapper.createPayHistory(payCoin);
		
		mapper.create(userItem);
	}
	
	@Override
	public List<UserItem> list(int userNo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.list(userNo);
	}
	
	@Override
	public UserItem read(int userItemNo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(userItemNo);
	}
	

}
