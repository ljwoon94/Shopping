package com.shopping.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.mapper.MessageMapper;

public class MessageSourceImpl implements MessageSource {
	@Autowired
	private MessageMapper mapper;
	
	@Override
	public String getMessage(String string, Object object, Locale korean) {
		// TODO Auto-generated method stub
		return null;
	}
}
