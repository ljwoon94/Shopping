package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.LoginLog;

public interface LoginLogMapper {

	//로그인 로깅 처리
	public void create(LoginLog loginLog) throws Exception;

	public List<LoginLog> list() throws Exception;

}
