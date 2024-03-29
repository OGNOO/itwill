package com.itwill.enum01;

public class Season2 {

	public static final Season2 SPRING = new Season2("봄");
	public static final Season2 SUMMER = new Season2("여름");
	public static final Season2 FALL = new Season2("가을");
	public static final Season2 WINTER = new Season2("겨울");

	private String neme;

	private Season2(String name) {
		this.neme = name;
	}

	public String getNeme() {
		return neme;
	}
}
