package com.shopping.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* @RequiredArgsConstructor
 * 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 
 * 생성자를 생성해 준다.*/
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CodeLabelValue {
	private final String value;
	private final String label;
}
