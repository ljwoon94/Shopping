package com.shopping.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.shopping.security.CustomAccessDeniedHandler;
import com.shopping.security.CustomLoginSuccessHandler;
import com.shopping.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//데이터 소스
	@Autowired
	DataSource dataSource;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin()
		.loginPage("/auth/login")
		.loginProcessingUrl("/login")
		//CustomLoginSuccessHandler를 로그인 성공 처리자로 지정한다.
		.successHandler(createAuthenticationSuccessHandler());
		http.logout()
		.logoutUrl("/auth/logout")
		.invalidateHttpSession(true)
		//로그아웃을 하면 자동 로그인에 사용하는 쿠키고 삭제해 주도록 한다.
		.deleteCookies("remeber-me","JESSION_ID");
		
		http.exceptionHandling()
		//CustomAccessDeniedHandler를 접근 거부 처리자로 지정한다.
		.accessDeniedHandler(createAccessDeniedHandler());
		//데이터 소스를 지정하고 테이블을 이용해서 기존 로그인 정보를 기록
		http.rememberMe()
		.key("hdcd")
		.tokenRepository(createJDBCRepository())
		//쿠키의 유효시간을 지정한다.
		.tokenValiditySeconds(60*60*24);
	
	}

	//CustomUserDetailsService를 스프링 빈으로 정의한다.
	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//CustomLoginSuccessHandler를 스프링 빈으로 정의한다.
	@Bean
	public AuthenticationSuccessHandler createAuthenticationSuccessHandler(){
		return new CustomLoginSuccessHandler();
	}
	
	//CustomAccessDeniedHandler를 스프링 빈으로 정의한다.
	@Bean
	public AccessDeniedHandler createAccessDeniedHandler() {
		// TODO Auto-generated method stub
		return new CustomAccessDeniedHandler();
	}
	
	//CustomUserDetailsService 빈을 인증 제공자에 지정한다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(createUserDetailsService())
		.passwordEncoder(createPasswordEncoder());
	}
	
	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}
}