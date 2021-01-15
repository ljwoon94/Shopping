package com.shopping.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginLog {
	private int userNo;
	private String userId;
	private String remoteAddr;
	private Date regDate;
}
