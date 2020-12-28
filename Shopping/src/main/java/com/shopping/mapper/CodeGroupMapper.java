package com.shopping.mapper;

import java.util.List;

import com.shopping.domain.CodeGroup;

public interface CodeGroupMapper {
	public void create(CodeGroup codeGroup) throws Exception;
	public List<CodeGroup> list() throws Exception;
}
