package com.shopping.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;

@Log
@Controller
public class CommonController {
	
	@GetMapping(value="/error/errorCommon")
	public void handleCommonError() {
		log.info("errorCommon");
	}
	
	@GetMapping(value="/error/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied: " + auth);
		model.addAttribute("msg","Access Denied");
	}
}
