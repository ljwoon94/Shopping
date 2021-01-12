package com.shopping.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.ChargeCoin;
import com.shopping.domain.Member;
import com.shopping.security.domain.CustomUser;
import com.shopping.service.CoinService;


@Controller
@RequestMapping("/coin")
public class CoinController {

	@Autowired
	private CoinService service;
	
	@Autowired
	private MessageSource messageSource;
	
	//코인 충전 화면
	@GetMapping(value="/charge")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void chargeForm(Model model) throws Exception{
		ChargeCoin chargeCoin = new ChargeCoin();
		chargeCoin.setAmount(1000);
		model.addAttribute(chargeCoin);
	}
	
	//코인충전 처리
	@PostMapping(value="/charge")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String charge(int amount,RedirectAttributes rttr,
			Authentication authentication) throws Exception{
		CustomUser customUser =(CustomUser)
				authentication.getPrincipal();
		
		Member member = customUser.getMember();
		int userNo= member.getUserNo();
		ChargeCoin chargeCoin = new ChargeCoin();
		chargeCoin.setUserNo(userNo);
		chargeCoin.setAmount(amount);
		service.charge(chargeCoin);
		
		String message = messageSource.getMessage("coin.chargingComplete",null,Locale.KOREAN);
		rttr.addFlashAttribute("msg", message);
		return "redirect:/coin/success";
	}
	
	//코인 충전 성공 화면
	@GetMapping(value="success")
	public String success() throws Exception{
		return "coin/success";
	}
	
	//충전 내역 화면
	@GetMapping(value="/list")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void list(Model model,Authentication authentication) throws Exception{
		CustomUser customUser =(CustomUser)
				authentication.getPrincipal();
		
		Member member = customUser.getMember();
				
		int userNo = member.getUserNo();
		model.addAttribute("list",service.list(userNo));
	}
	
	//구매 내역 화면
	@GetMapping(value="/listPay")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public void listPayHistory(Model model, Authentication authentication) throws Exception{
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		
		Member member = customUser.getMember();
		int userNo = member.getUserNo();
		
		model.addAttribute("list", service.listPayHistory(userNo));
	}
	
}
