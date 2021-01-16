package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.domain.PerformanceLog;
import com.shopping.mapper.PerformanceLogMapper;

public class PerformanceLogServiceImpl implements PerformanceLogService {
	
	@Autowired
	private PerformanceLogMapper mapper;
	//서비스 성능 로깅 처리
	@Override
	public void register(PerformanceLog performanceLog) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(performanceLog);
	}

	@Override
	public List<PerformanceLog> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}

}
