package com.shopping.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageRequest {
	private int page;
	private int sizePerPage;
	
	public PageRequest() {
		this.page = 1;
		this.sizePerPage = 10;
	}
	
	public void setPage(int page) {
		if(page<=0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getPage() {
		return page;
	}
	
	//마이바티스 SQL매퍼를 위한 메서드
	public int getPageStart() {
		return (this.page-1) * sizePerPage;
	}
	
	public int getSizePerPage() {
		return this.sizePerPage;
	}
	
	//맴버변수를 활용하여 쿼리파라미터를 생성한다.
	public String toUriStringByPage(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.build();

		return uriComponents.toUriString();
	}
	
	public String toUriString() {
		return toUriStringByPage(this.page);
	}
}
