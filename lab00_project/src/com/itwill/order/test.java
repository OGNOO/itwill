package com.itwill.order;

import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isValidNumber("99999"));;
	}
	public static boolean isValidNumber(String input) {
        Pattern pattern = Pattern.compile("^[0-9]{1,5}$");
        return pattern.matcher(input).matches();
    }

}
