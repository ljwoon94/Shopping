package com.shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/*
스프링 타일즈는 뷰단의 탑,사이드메뉴,하단,메인 등을 페이지 include 방식으로 나누는 
기존구조를 쉽게 적용하기 위한 템플릿 프레임워크이다
장점은 include 디자인을 변경하면 페이지를 전체적으로 수정해야하는 번거로움을 없애고
일관적인 페이지 관리를 가능하도록 한다
*/

@Configuration
public class TilesConfig {
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"/WEB-INF/tiles/tiles.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		
		return tilesViewResolver;
	}
}
