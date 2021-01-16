package com.shopping.service;

import java.util.List;

import com.shopping.domain.PerformanceLog;

public interface PerformanceLogService {
	//서비스 성능 로깅 처리
	public void register(PerformanceLog performanceLog) throws Exception;

	public List<PerformanceLog> list() throws Exception;
}
