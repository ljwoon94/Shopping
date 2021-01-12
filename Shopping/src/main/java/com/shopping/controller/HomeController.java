package com.shopping.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

	@GetMapping(value="/")
	/* Locale 객체는 지리적, 국가적, 문화적 지역을 다루는 클래스로 지역적 분류에 따라 여러 가지 처리를 자동으로 한다.
	 * 대표적으로 시간 표현 등이 있다. 현재 시간을 표현하기위해 사용. 
	 * Model 객체는  스프링에서 제공해주는 데이터 공유 객체이다.
	 * Model 객체 안에 데이터를 담는다. key값과 value 값을로 구성된다.
	 * */
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate =dateFormat.format(date);
		
		/* model.addAttribute("name",value);
		 * value객체를 name이름으로 추가한다, 뷰 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다
		 * */
		model.addAttribute("serverTime",formattedDate);
		return "home";
	}
}
