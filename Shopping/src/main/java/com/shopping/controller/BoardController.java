package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.Board;
import com.shopping.domain.Member;
import com.shopping.security.domain.CustomUser;
import com.shopping.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void registerForm(Model model, Authentication authentication) throws Exception{
		//로그인한 사용자 정보 획득
		CustomUser customUser = (CustomUser)
		authentication.getPrincipal();
		Member member = customUser.getMember();
		
		Board board = new Board();
		//로그인한 사용자 아이디를 등록화면에 표시
		board.setWriter(member.getUserId());
		model.addAttribute(board);
	}
	@PostMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String register(Board board, RedirectAttributes rttr) throws Exception{
		service.register(board);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/list";
	}
	
	@GetMapping(value="/list")
	public void list(Model model) throws Exception{
		model.addAttribute("list",service.list());
	}
	
	//회원 게시글 상세화면
	@GetMapping(value="/read")
	public void read(int boardNo, Model model) throws Exception{
		model.addAttribute(service.read(boardNo));
	}
	
	//게시글 수정 화면
	@GetMapping(value="/modify")
	public void modifyForm(int boardNo, Model model) throws Exception{
		model.addAttribute(service.read(boardNo));
	}
	
	//게시글 수정
	@PostMapping(value="/modify")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public String modify(Board board, RedirectAttributes rttr) throws Exception{
		
		service.modify(board);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	} 
	
	//게시글 삭제
	@PostMapping(value="/remove")
	@PreAuthorize("hasAnyRole('ROLE_ADMINA','ROLE_MEMBER')")
	public String remove(int boardNo, RedirectAttributes rttr) throws Exception{
		service.remove(boardNo);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/list";
	}
}
