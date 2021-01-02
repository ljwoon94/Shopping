package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.service.CodeService;
import com.shopping.service.MemberService;

@Controller
@RequestMapping(value="/user")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private CodeService codeService;
	
	// 스프링 시큐리티의 비밀번호 암호 처리기
	@Autowired
	private PasswordEncoder passwodEncoder;
}
