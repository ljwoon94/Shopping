package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.CodeDetail;
import com.shopping.domain.CodeLabelValue;
import com.shopping.service.CodeDetailService;
import com.shopping.service.CodeService;

@Controller
@RequestMapping("/codedetail")
//관리자 권한을 가진 사용자만 접근이 가능하다.
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CodeDetailController {
	@Autowired
	private CodeDetailService codeDetailService;
	
	@Autowired
	private CodeService codeService;
	
	//코드 등록 화면
	@GetMapping(value="/register")
	public void registerForm(Model model) throws Exception{
		CodeDetail codeDetail = new CodeDetail();
		model.addAttribute(codeDetail);
		
		//그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList= codeService.getCodeGroupList();
		model.addAttribute("groupCodeList",groupCodeList);
	}
	//코드 등록 처리
	@PostMapping(value="/register")
	public String register(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception{
		codeDetailService.register(codeDetail);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/codedetail/list";
	}
	//목록
	@GetMapping(value="/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list",codeDetailService.list());
	}
	
	//상세화면
	@GetMapping(value="/read")
	public void read(CodeDetail codeDetail, Model model) throws Exception{
		model.addAttribute(codeDetailService.read(codeDetail));
		//그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}
	
	//코드 수정 화면
	@GetMapping(value="/modify")
	public void modifyForm(CodeDetail codeDetail, Model model) throws Exception{
		model.addAttribute(codeDetailService.read(codeDetail));
		//그룹코드 목록을 조회하여 뷰에 전달
		List<CodeLabelValue> groupCodeList = codeService.getCodeGroupList();
		model.addAttribute("groupCodeList", groupCodeList);
	}	
	//코드 수정
	@PostMapping(value="/modify")
	public String modify(CodeDetail codeDetail,RedirectAttributes rttr) throws Exception{
		codeDetailService.modify(codeDetail);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/codedetail/list";
	}
	//코드 삭제
	@PostMapping(value="/remove")
	public String remove(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception{
		codeDetailService.remove(codeDetail);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codedetail/list";
	}
}
