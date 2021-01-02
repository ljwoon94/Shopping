package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.CodeLabelValue;
import com.shopping.domain.Member;
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
	
	//회원 등록 화면
	@GetMapping(value="/register")
	public void registerForm(Member member, Model model) throws Exception{
		//직업코드 목록을 조회하여 뷰에 전달
		String groupCode="A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);
		model.addAttribute("jobList",jobList);
		
	}
	//회원 등록 처리
	@PostMapping(value="/register")
	public String register(@Validated Member member,
							BindingResult result, Model model, 
							RedirectAttributes rttr) throws Exception{
		String groupCode="A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		return "user/register";
	}
}
