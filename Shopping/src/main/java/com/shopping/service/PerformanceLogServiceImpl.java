package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.PerformanceLog;
import com.shopping.mapper.PerformanceLogMapper;

@Service
public class PerformanceLogServiceImpl implements PerformanceLogService {

	@Autowired
	private PerformanceLogMapper mapper;
	
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
