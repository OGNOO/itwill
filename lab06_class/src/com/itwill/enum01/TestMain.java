package com.itwill.enum01;

public class TestMain {

	public static void main(String[] args) {
		int season = 4;
		switch (season) {
		case Season.SPRING: {
			System.out.println("봄");
			break;
		}
		case Season.SUMMER: {
			System.out.println("여름");
			break;
		}
		case Season.FALL: {
			System.out.println("가을");
			break;
		}
		case Season.WINTER: {
			System.out.println("겨울");
			break;
		}
		default:
			System.out.println("이상기후");
		}

		Season2 season2 = Season2.SPRING;
		if (season2 == Season2.SPRING) {
			System.out.println("봄");
		} else if (season2 == Season2.SUMMER) {
			System.out.println("여름");
		} else {
			System.out.println("...");
		}

		System.out.println(Season2.SPRING.getNeme());
	}

}
