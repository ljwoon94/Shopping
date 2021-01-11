package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.domain.Member;
import com.shopping.security.domain.CustomUser;
import com.shopping.service.UserItemService;

@Controller
@RequestMapping("/useritem")
public class UserItemController {
	@Autowired
	private UserItemService service;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	//구매 상품 목록 화면
	@GetMapping(value="/list")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void list(Model model,Authentication authentication) throws Exception{
		CustomUser customUser =(CustomUser)authentication.getPrincipal();
		
		Member member = customUser.getMember();
		int userNo = member.getUserNo();
		model.addAttribute("list", service.list(userNo));
	}
	
	//구매 상품 보기 화면
	@GetMapping(value="/read")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void read(int userItemNo, Model model) throws Exception{
		model.addAttribute(service.read(userItemNo));
	}
}
