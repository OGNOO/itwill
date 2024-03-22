package com.itwill.loop13;

public class LoopMain13 {

	public static void main(String[] args) {
		// 교재 연습문제 5
		// 출력 결과
		// *
		// **
		// ***
		// ****

		for (int i = 1; i < 5; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print("*");

			}
			System.out.println();
		}

		// 교재 연습문제 6
		// 출력 결과
		// *
		// **
		// ***
		// ****
		System.out.println();

		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j < 5 - i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}

		// 문제 7
		
		
	}

}
