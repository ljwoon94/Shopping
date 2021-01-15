package com.shopping.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.shopping.domain.LoginLog;
import com.shopping.domain.Member;
import com.shopping.security.domain.CustomUser;
import com.shopping.service.LoginLogService;
import com.shopping.util.NetUtils;

import lombok.extern.java.Log;

@Log
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	@Autowired
	private LoginLogService service;
	
	//로그인 성공 처리자 메서드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member=customUser.getMember();
	
		log.info("UserId = " + member.getUserId());
		
		//로그인에 성공한 사용자의 ip정보와 사용자정보를 로깅처리한다.
	    String remoteAddr = NetUtils.getIp(request);
		
		log.info("remoteAddr = " + remoteAddr);
		
		LoginLog loginLog = new LoginLog();
		
		loginLog.setUserNo(member.getUserNo());
		loginLog.setUserId(member.getUserId());
		loginLog.setRemoteAddr(remoteAddr);
		
		try {
			service.register(loginLog);
		} catch (Exception e) {
			
		}
		super.onAuthenticationSuccess(request,response,authentication);
	}

}
