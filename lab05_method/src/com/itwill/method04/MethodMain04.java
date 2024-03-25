package com.itwill.method04;

import java.util.Random;

public class MethodMain04 {

	public static void main(String[] args) {
		// 다양한 리턴 타입:
		// Ex1. isEven(정수): 정수가 짝수이면 true, 그렇지 않으면 false를 리턴
		Random random = new Random();

		int number = random.nextInt(100);
		System.out.println("Ex1.\n");
		System.out.println("number = " + number);

		boolean result = isEven(number);
		System.out.println("isEven = " + result);
		System.out.println("========================\nEx2.\n");
		// Ex2.
		int x = random.nextInt(100);
		int y = random.nextInt(100);
		System.out.println("x=" + x + ", y=" + y);

		int big = whoIsBig(x, y); // x, y 중 크거나 같은 수를 리턴.
		System.out.println("big=" + big);
		System.out.println("========================\nEx3.\n");
		// Ex3.
		int code = random.nextInt(1, 5);
		String gender = parseGenderCode(code); // 1 또는 3이면 "male", 2 또는 4이면 "female", 그 이외에는 "unknown"
		System.out.println("code=" + code + ", gender=" + gender);
	}

	/**
	 * 정수 1개를 전달받아서 짝수면 true, 홀수면 false 를 반환
	 * 
	 * @param x (int) 짝수/홀수를 검사할 정수
	 * @return trueOrFalse x가 짝수면 true, 홀수면 false
	 */
	public static boolean isEven(int x) {
//		if (x % 2 == 0) {
//			return  true;
//		} else {
//			return  false;
//		}

		boolean trueOrFalse;

		if (x % 2 == 0) {
			trueOrFalse = true;
		} else {
			trueOrFalse = false;
		}

		return trueOrFalse;
	}

	/**
	 * 정수 2개를 전달받아서 둘 중 큰 수를 반환
	 * 
	 * @param x (int)
	 * @param y (int)
	 * @return big x > y 이면 x, 그렇지 않으면 y
	 */
	public static int whoIsBig(int x, int y) {
		int big;
		if (x >= y) {
			big = x;
		} else {
			big = y;
		}
		return big;
	}

	/**
	 * 정수 1개를 전달받아서 1 또는 3이면 "male", 2 또는 4이면 "female", 그렇지 않으면 "unknown" 을 반환
	 * 
	 * @param code (int)
	 * @return gender 1 또는 3이면 "male", 1 또는 3이면 "male", 2 또는 4이면 "female", 그렇지 않으면
	 *         "unknown" 을 반환
	 */
	public static String parseGenderCode(int code) {
		String gender;
		if (code == 1 || code == 3) {
			gender = "male";
		} else if (code == 2 || code == 4) {
			gender = "female";
		} else { 
			gender = "unknown";
		}
		return gender;
	}

}
