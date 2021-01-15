package com.shopping.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.domain.Member;
import com.shopping.domain.UserItem;
import com.shopping.exception.NotMyItemException;
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
	
	@ResponseBody
	@RequestMapping("/download")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public ResponseEntity<byte[]> download(int userItemNo, 
			Authentication authentication) throws Exception{
		
		UserItem userItem =service.read(userItemNo);
		
		//구매한 상품이 사용자의 것인지 체크한다.
		CustomUser customUser =(CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		if(userItem.getUserNo() != member.getUserNo()) {
			throw new NotMyItemException("It is Not My Item.");
		}
		
		String fullName = userItem.getPictureUrl();
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + File.separator + fullName);
			String fileName = fullName.substring(fullName.indexOf("_")+1);
			
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename=\""+
			new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,HttpStatus.CREATED);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>
			(HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
	}
	
	//본인 상품 예외 처리
	@GetMapping(value="/notMyItem")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public void notMyItem(Model model) throws Exception{
		
		
	}
}
