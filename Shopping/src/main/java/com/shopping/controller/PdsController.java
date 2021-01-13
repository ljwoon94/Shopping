package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.Pds;
import com.shopping.service.PdsService;

@Controller
@RequestMapping("/pds")
public class PdsController {
	
	@Autowired
	private PdsService pdsService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping("/getAttach/{itemId}")
	public List<String> getAttach(@PathVariable("itemId") Integer itemId) throws Exception{
		return pdsService.getAttach(itemId);
	}
	
	//공개자료 등록 화면
	@GetMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registerForm(Model model) {
		model.addAttribute(new Pds());
		return "pds/register";
	}
	
	//공개자료 등록 처리
	@PostMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Pds pds, RedirectAttributes rttr) throws Exception{
		this.pdsService.register(pds);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/pds/list";
	}
}
