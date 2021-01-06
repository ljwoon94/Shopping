package com.shopping.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private int userNo;
	
	/* Not Blank는 null, "", " " 을 허용하지 않는다. */
	@NotBlank
	private String userId;
	@NotBlank
	private String userPw;
	@NotBlank
	private String userName;
	
	private String job;
	private int coin;
	private boolean enabled;
	private Date regDate;
	private Date updDate;
	private List<MemberAuth> authList;
}
