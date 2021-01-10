package com.shopping.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageRequest {
	private int page;
	private int sizePerPage;
	
	//검색 유형과 검색어를 멤버변수로 선언한다
	private String searchType;
	private String keyword;
	
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
	
	//검색유형과 검색어를 멤버변수의 Getter/Setter 메서드
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	//맴버변수를 활용하여 쿼리파라미터를 생성한다.
	public String toUriStringByPage(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				//검색타입과 검색어를 추가, 쿼리파라미터를 생성
				.queryParam("searchType",this.searchType)
				.queryParam("keyword",this.keyword)
				.build();

		return uriComponents.toUriString();
	}
	

	public String toUriString() {
		return toUriStringByPage(this.page);
	}

	//검색폼의 액션 URI를 생성한다.
	public String toUriStringForSearchAction(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.build();
		
		return uriComponents.toUriString();
	}
	
	
	
	
	
}
