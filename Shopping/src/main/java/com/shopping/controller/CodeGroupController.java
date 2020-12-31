package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.CodeGroup;
import com.shopping.service.CodeGroupService;

@Controller
@RequestMapping("/codegroup")
public class CodeGroupController {

	@Autowired
	private CodeGroupService service;
	
	//코드그룹 등록 화면
	@GetMapping(value="/register")
	public void registerForm(Model model) throws Exception{
		CodeGroup codeGroup = new CodeGroup();
		model.addAttribute(codeGroup);
	}
	
	//코드그룹 등록
	@PostMapping(value="/register")
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception{
		service.register(codeGroup);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/codegroup/list";
	}
	
	//코드그룹 목록 화면
	@GetMapping(value="/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list",service.list());
	}
	
	//상세 화면
	@GetMapping(value="/read")
	public void read(String groupCode,Model model) throws Exception{
		model.addAttribute(service.read(groupCode));
	}

}
