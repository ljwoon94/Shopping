package com.shopping.service;

import java.util.List;

import com.shopping.domain.CodeDetail;

public interface CodeDetailService {

	public void register(CodeDetail codeDetail) throws Exception;
	public List<CodeDetail> list() throws Exception;
}
