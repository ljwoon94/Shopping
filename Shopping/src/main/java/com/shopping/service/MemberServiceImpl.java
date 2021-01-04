package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.domain.Member;
import com.shopping.domain.MemberAuth;
import com.shopping.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper mapper;
	
	//등록처리
	@Transactional
	@Override
	public void register(Member member) throws Exception{
		mapper.create(member);
		//회원권한 생성
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_MEMBER");
		mapper.createAuth(memberAuth);
	}
	
	//상세화면
	@Override
	public Member read(int userNo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(userNo);
	}
	
	//수정처리
	@Transactional
	@Override
	public void modify(Member member) throws Exception{
		mapper.update(member);
	
		//회원권한 수정
		int userNo = member.getUserNo();
		mapper.deleteAuth(userNo);
		List<MemberAuth> authList = member.getAuthList();
		for(int i = 0; i <authList.size();i++) {
			MemberAuth memberAuth  = authList.get(i);
			String auth = memberAuth.getAuth();
		
			if(auth == null) {
				continue;
			}
		
			if(auth.trim().length()==0) {
				continue;
			}
		
			memberAuth.setUserNo(userNo);
			mapper.createAuth(memberAuth);
		}
	}
	
	//삭제처리
	@Transactional
	@Override
	public void remove(int userNo) throws Exception{
		//회원권한 삭제
		mapper.deleteAuth(userNo);
		mapper.delete(userNo);
	}
	
	//목록 화면
	@Override
	public List<Member> list() throws Exception{
		return mapper.list();
	}
}
