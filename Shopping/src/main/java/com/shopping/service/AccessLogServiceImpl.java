package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.AccessLog;
import com.shopping.mapper.AccessLogMapper;

@Service
public class AccessLogServiceImpl implements AccessLogService {

	@Autowired
	private AccessLogMapper mapper;
	
	//접근 로깅 처리
	@Override
	public void register(AccessLog accessLog) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(accessLog);
	}
	
	@Override
	public List<AccessLog> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}

}
