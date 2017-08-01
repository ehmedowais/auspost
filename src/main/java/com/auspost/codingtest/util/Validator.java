package com.auspost.codingtest.util;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class Validator {
	public static boolean isValidPostcode(String postcode){
		Pattern pattern = Pattern.compile("(\\d{4})");
		if(StringUtils.isEmpty(postcode) || !pattern.matcher(postcode).matches()){
			return false;
		}
		return true;
	}
}
