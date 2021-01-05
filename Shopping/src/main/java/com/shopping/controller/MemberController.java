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
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private CodeService codeService;
	
	// 스프링 시큐리티의 비밀번호 암호 처리기
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		if(result.hasErrors()) {
		//직업코드 목록을 조회하여 뷰에 전달
		String groupCode="A01";
		List<CodeLabelValue> jobList = codeService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		return "user/register";
	}
	//회원 비밀번호 암호화
	String inputPassword = member.getUserPw();
	member.setUserPw(passwordEncoder.encode(inputPassword));
	
	service.register(member);
	rttr.addFlashAttribute("userName",member.getUserName());
	return "redirect:/user/registerSuccess";
	}
	
	//회원 등록 성공 화면
	@GetMapping(value="/registerSuccess")
	public void registerSuccess(Model model) throws Exception{
		
	} 
	
	//회원 목록 화면
	@GetMapping(value="/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list",service.list());
	}
	
	//회원 상세 화면
	@GetMapping(value="/read")
	public void read(int userNo, Model model) throws Exception{
	//직접코드 목록은 조회하여 뷰에 전달	
		String groupCode="A01";
		List<CodeLabelValue> jobList= codeService.getCodeList(groupCode);
		model.addAttribute("jobList",jobList);
		model.addAttribute(service.read(userNo));
	}
	
	//회원 삭제 처리
	@PostMapping(value="/remove")
	public String remove(int userNo,RedirectAttributes rttr) throws Exception{
		service.remove(userNo);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/user/list";
	}
	//회원 수정 화면
	@GetMapping(value="/modify")
	public void modifyForm(int userNo, Model model) throws Exception{
		//직업코드 목록을 조회하여 뷰에 전달
		String groupCode="A01";
		List<CodeLabelValue> jobList= codeService.getCodeList(groupCode);
		model.addAttribute("jobList",jobList);
		
		model.addAttribute(service.read(userNo));
	}
	//회원 수정 처리
	@PostMapping(value="/modify")
	public String modify(Member member, RedirectAttributes rttr) throws Exception{
		service.modify(member);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/user/list";
	}
	
	//최초 관리자 생성 화면
	@GetMapping(value="/setup")
	public String setupAdminForm(Member member, Model model) throws Exception{
		//회원테이블 데이터 건수 확인하여 최초관리자 등록화면에 표시
		if(service.countAll()==0) {
			return "user/setup";
		}
		//회원테이블에 데이터가 존재하면 최초관리자를 생성할 수 없으므로 실패화면을 표시
		return "user/setupFailure";
	}
	//회원 테이블에 데이터가 없으면 최초 관리자를 생성한다.
	@PostMapping(value="/setup")
	public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception{
		//회원테이블 데이터 건수를 확인하ㅕ 빈 테이블이면 최초관리자를 생성
		if(service.countAll()==0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			member.setJob("00");
			service.setupAdmin(member);
			rttr.addFlashAttribute("userName", member.getUserName());
			return "redirect:/user/registerSuccess";
		}
		//회원테이블에 데이터가 존재하면 최초관리자를 생성할 수 없으므로 실패화면을 표시
		return "redirect:/user/setupFailure";
	}
	
}
