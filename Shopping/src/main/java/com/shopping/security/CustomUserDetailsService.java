package com.shopping.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shopping.domain.Member;
import com.shopping.mapper.MemberMapper;
import com.shopping.security.domain.CustomUser;

import lombok.extern.java.Log;

@Log
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("Load User By UserName: " + userName);
		Member member = memberMapper.readByUserId(userName);
		log.info("queried by member mapper: " + member);
		
		return member == null ? null : new CustomUser(member);
	}

}
