package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.PerformanceLog;

public interface PerformanceLogMapper {
	//등록처리
	public void create(PerformanceLog performanceLog) throws Exception;

	public List<PerformanceLog> list() throws Exception;

}
