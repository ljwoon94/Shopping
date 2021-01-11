package com.shopping.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.domain.Item;
import com.shopping.domain.Member;
import com.shopping.security.domain.CustomUser;
import com.shopping.service.ItemService;
import com.shopping.service.MemberService;
import com.shopping.service.MessageSource;
import com.shopping.service.UserItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserItemService userItemService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	//상품 등록 화면
	@GetMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registerForm(Model model) {
		model.addAttribute(new Item());
		return "item/register";
	}
	
	//상품등록 처리
	@PostMapping(value="/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(Item item,RedirectAttributes rttr) throws Exception{
		MultipartFile pictureFile = item.getPicture();
		MultipartFile previewFile = item.getPreview();
		
		String createdPictureFilename = uploadFile(pictureFile.getOriginalFilename(),pictureFile.getBytes());
		String createdPreviewFilename = uploadFile(previewFile.getOriginalFilename(),previewFile.getBytes());
		
		item.setPictureUrl(createdPictureFilename);
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
	
	//상품 상세화면
	@GetMapping(value="/read")
	public String read(Integer itemId, Model model) throws Exception{
		Item item = itemService.read(itemId);
		model.addAttribute(item);
		return "item/read";
	}
	
	//상품 수정화면
	@GetMapping(value="/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modifyForm(Integer itemId, Model model) throws Exception{
		Item item = itemService.read(itemId);
		model.addAttribute(item);
		return "item/modify";
	}
	
	//상품 수정 처리
	@PostMapping(value="/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(Item item, RedirectAttributes rttr) throws Exception{
		MultipartFile pictureFile = item.getPicture();
		
		if(pictureFile != null && pictureFile.getSize()>0) {
			String createdFilename = uploadFile(pictureFile.getOriginalFilename(),
					pictureFile.getBytes());
		
			item.setPictureUrl(createdFilename);
		}
		
		MultipartFile previewFile = item.getPreview();
		
		if(previewFile != null && previewFile.getSize()>0) {
			String createdFilename = uploadFile(previewFile.getOriginalFilename(),
					previewFile.getBytes());
		
			item.setPreviewUrl(createdFilename);
		}
		
		itemService.modify(item);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/item/list";
	}
	
	//제품 삭제 화면
	@GetMapping(value="/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String removeForm(Integer itemId, Model model) throws Exception{
		Item item = itemService.read(itemId);
		model.addAttribute(item);
		return "item/remove";
	}
	
	//제품 삭제 처리
	@PostMapping(value="/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(Item item, RedirectAttributes rttr) throws Exception{
		itemService.remove(item.getItemId());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/item/list";
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception{
		// TODO Auto-generated method stub
		
		UUID uid = UUID.randomUUID();
		String createdFileName = uid.toString()+"_"+originalName;
		File target = new File(uploadPath,createdFileName);
		FileCopyUtils.copy(fileData,target);
		
		return createdFileName;
	}

	//상품구매처리
	@PostMapping(value="/buy")
	public String buy(Integer itemId,RedirectAttributes rttr,
			Authentication authentication) throws Exception{
		CustomUser customUser = (CustomUser)authentication.getPrincipal();
		Member member = customUser.getMember();
		
		int userNo = member.getUserNo();
		member.setCoin(memberService.getCoin(userNo));
		
		Item item = itemService.read(itemId);
		userItemService.register(member, item);
		
		String message = messageSource.getMessage("item.purchaseComplete", null, Locale.KOREAN);
		rttr.addFlashAttribute("msg", message);
		
		return "redirect:/item/success";
	
	}
	
	//상품 구매 성공 화면
	@GetMapping(value="/success")
	public String success() throws Exception{
		return "item/success";
	}
	
	
	//원본 이미지 표시
	@ResponseBody
	@RequestMapping(value="/picture")
	public ResponseEntity<byte[]> pictureFile(Integer itemId) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String fileName = itemService.getPicture(itemId);
		
		try {
			String formatName =
					fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType = getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,HttpStatus.CREATED);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	//미리보기 이미지 표시
	@ResponseBody
	@RequestMapping(value="/display")
	public ResponseEntity<byte[]> displayFile(Integer itemId) throws Exception{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		String fileName = itemService.getPreview(itemId);
		
		try {
			String formatName =
					fileName.substring(fileName.lastIndexOf(".")+1);
			
			MediaType mType = getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,HttpStatus.CREATED);
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		
		return entity;
	}
	
	//파일 확장자로 이미지 형식 확인
	private MediaType getMediaType(String formatName) {
		if(formatName != null) {
			if(formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			if(formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
			if(formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}
		return null;
	}	
}