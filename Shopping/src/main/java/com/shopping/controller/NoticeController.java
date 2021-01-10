package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.Notice;
import com.shopping.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	//공지사항 등록 화면
	@GetMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void registerForm(Model model) throws Exception{
		Notice notice = new Notice();
		model.addAttribute(notice);
		
	}
	//공지사항 등록 처리
	@PostMapping(value="register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Notice notice, RedirectAttributes rttr) throws Exception{
		service.register(notice);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}
	
	//공지사항 목록
	@GetMapping(value="/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list", service.list());
	}
	
	//공지사항 상세화면
	
	@GetMapping(value="/read")
	public void read(int noticeNo, Model model) throws Exception{
		model.addAttribute(service.read(noticeNo));
	}
	
	//공지사항 수정 화면
	@GetMapping(value="/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void modifyForm(int noticeNo, Model model) throws Exception{
		model.addAttribute(service.read(noticeNo));
	}
	
	//공지사항 수정 처리
	@PostMapping(value="/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Notice notice,RedirectAttributes rttr) throws Exception{
		service.modify(notice);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/notice/list";
	}
	
	//공지사항 삭제 처리
	@PostMapping(value="/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(int noticeNo, RedirectAttributes rttr) throws Exception{
		service.remove(noticeNo);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/notice/list";
	}
	
}
