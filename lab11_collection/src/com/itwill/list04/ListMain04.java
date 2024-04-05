package com.itwill.list04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMain04 {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("java", "sql", "html", "css", "javascript", "servlet", "jsp", "spring");

		// 1. names 에서 5글자 이상인 문자열을 대문자로 변환해서 저장하는 ArrayList를 만들고 출력.
		// -> [JAVASCRIPT, SERVLET, SPRING]
		ArrayList<String> arr1 = new ArrayList<>();

		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).length() >= 5) {
				arr1.add(names.get(i).toUpperCase());
			}
		}
		System.out.println(arr1);

		// 2. names 에 저장된 문자열의 글자수들을 저장하는 ArrayList를 만들고 출력.
		// -> [4, 3, 4, 3, 10, 7, 3, 6]
		ArrayList<Integer> arr2 = new ArrayList<>();

		for (int i = 0; i < names.size(); i++) {
			arr2.add(names.get(i).length());
		}
		System.out.println(arr2);

		// 3. codes 의 원소가 0이면 "남성", 1이면 "여성"을 저장하는 ArrayList를 만들고 출력.
		List<Integer> codes = Arrays.asList(0, 1, 0, 1, 1, 0);
		ArrayList<String> arr3 = new ArrayList<>();

		for (int i = 0; i < codes.size(); i++) {
			if (codes.get(i) == 1) {
				arr3.add("여성");
			} else {
				arr3.add("남성");
			}
		}
		System.out.println(arr3);
	}
}
