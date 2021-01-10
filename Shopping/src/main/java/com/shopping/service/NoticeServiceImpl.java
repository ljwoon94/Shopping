package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.domain.Notice;
import com.shopping.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public void register(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(notice);
	}
	
	@Override
	public List<Notice> list() throws Exception {
		// TODO Auto-generated method stub
		return mapper.list();
	}
	
	@Override
	public Notice read(Integer noticeNo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(noticeNo);
	}
	@Override
	public void modify(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		mapper.update(notice);
	}
	
	@Override
	public void remove(Integer noticeNo) throws Exception {
		// TODO Auto-generated method stub
		mapper.delete(noticeNo);
	}
}
