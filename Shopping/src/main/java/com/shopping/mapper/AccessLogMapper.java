package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.AccessLog;

public interface AccessLogMapper {
	//등록처리
	public void create(AccessLog accessLog) throws Exception;

	public List<AccessLog> list() throws Exception;

}
