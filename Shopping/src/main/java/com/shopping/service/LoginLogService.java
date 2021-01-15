package com.shopping.service;

import java.util.List;

import com.shopping.domain.LoginLog;

public interface LoginLogService {
	//로그인 로깅 처리
	public void register(LoginLog loginLog) throws Exception;
	
	public List<LoginLog> list() throws Exception;
}
