package com.shopping.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.Item;
import com.shopping.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	//상품 등록 화면
	@GetMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registerForm(Model model) {
		model.addAttribute(new Item());
		return "Item/register";
	}
	
	//상품등록 처리
	@PostMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Item item,RedirectAttributes rttr) throws Exception{
		MultipartFile pictureFile = item.getPicture();
		MultipartFile previewFile = item.getPreview();
		
		String createdPictureFilename = uploadFile(pictureFile.getOriginalFilename(),pictureFile.getBytes());
		String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(),previewFile.getBytes());
		
		item.setPictureUri(createdPictureFilename);
		item.setPreviewUrl(createdPreviewFilename);
		
		itemService.register(item);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/item/list";
	}
	


	//상품 목록
	@GetMapping(value="/list")
	public void list(Model model) throws Exception{
		List<Item> itemList = itemService.list();
		model.addAttribute("itemList", itemList);
	}
	
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception{
		// TODO Auto-generated method stub
		
		UUID uid = UUID.randomUUID();
		String createdFileName = uid.toString()+"_"+originalName;
		File target = new File(uploadPath,createdFileName);
		FileCopyUtils.copy(fileData,target);
		
		return createdFileName;
	}
	
}
