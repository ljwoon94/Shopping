package com.shopping.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChargeCoin {
	
	private int historyNo;
	private int uesrNo;
	private int amount;
	private Date regDate;
}
