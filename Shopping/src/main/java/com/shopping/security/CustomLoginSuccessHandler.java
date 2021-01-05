package com.shopping.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.shopping.domain.Member;
import com.shopping.security.domain.CustomUser;

import lombok.extern.java.Log;

@Log
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	//로그인 성공 처리자 메서드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member=customUser.getMember();
	
		log.info("UserId = " + member.getUserId());
		super.onAuthenticationSuccess(request,response,authentication);
	}

}
