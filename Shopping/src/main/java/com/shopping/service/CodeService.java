package com.shopping.service;

import java.util.List;

import com.shopping.domain.CodeLabelValue;

public interface CodeService {
	//그룹코드 목록 조회
	public List<CodeLabelValue> getCodeGroupList() throws Exception;
}
