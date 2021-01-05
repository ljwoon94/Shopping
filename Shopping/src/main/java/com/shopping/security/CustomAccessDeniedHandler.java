package com.shopping.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.java.Log;

@Log
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	//접근 거부 처리자 메서드
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("Access Denied Handler");
		log.info("Redirect....");
		response.sendRedirect("/error/accessError");
	}

}
