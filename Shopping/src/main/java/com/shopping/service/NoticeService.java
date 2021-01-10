package com.shopping.service;

import java.util.List;

import com.shopping.domain.Notice;

public interface NoticeService {

	public void register(Notice notice) throws Exception;

	public List<Notice> list() throws Exception;

	public Notice read(Integer noticeNo) throws Exception;

	public void modify(Notice notice) throws Exception;

	public void remove(Integer noticeNo) throws Exception;

}
