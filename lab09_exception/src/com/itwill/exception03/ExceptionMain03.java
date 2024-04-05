package com.itwill.exception03;

import java.util.Scanner;

public class ExceptionMain03 {

	private final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ExceptionMain03 app = new ExceptionMain03();

		int x = app.inputInteger();
		System.out.println("x = " + x);
	}

//	private int inputInteger() {
//		boolean run = true;
//		int result = 0;
//
//		while (run) {
//			try {
//				System.out.print("정수 입력 >> ");
//				result = Integer.parseInt(sc.nextLine());
//				run = false;
//			} catch (NumberFormatException e) {
//				System.out.println("정수가 아닙니다.");
//			}
//		}
//
//		return result;
//	}

	private int inputInteger() {
		try {
			System.out.print("정수 입력 >> ");
			int reseult = Integer.parseInt(sc.nextLine());

			return reseult;
		} catch (NumberFormatException e) {
			System.out.println("정수가 아닙니다.");

			return inputInteger();
		}

	}
}
