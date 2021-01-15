package com.shopping.service;

import java.util.List;

import com.shopping.domain.AccessLog;

public interface AccessLogService {
	//접근 로깅 처리
	public void register(AccessLog accessLog) throws Exception;
	
	public List<AccessLog> list() throws Exception;
}
