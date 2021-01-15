package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.LoginLog;
import com.shopping.mapper.LoginLogMapper;

@Service
public class LoginLogServiceImpl implements LoginLogService {

	@Autowired
	private LoginLogMapper mapper;
	
	//로그인 로깅 처리
	@Override
	public void register(LoginLog loginLog) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(loginLog);
	}

	@Override
	public List<LoginLog> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}

}
