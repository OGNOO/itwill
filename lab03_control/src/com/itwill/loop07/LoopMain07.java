package com.itwill.loop07;

public class LoopMain07 {

	public static void main(String[] args) {
		// 과제: 구구단 출력하기
		// 2단은 2x2까지만, 3단은 3x3까지만, 4단은 4x4까지만, ..., 9단은 9x9까지 출력.
		// (1) break 이용하기.
		System.out.println("(1) break 이용하기");
		for (int i = 2; i < 10; i++) {
			System.out.println("===== " + i + "단 =====");
			for (int j = 1; j < 10; j++) {
				System.out.println(i + " x " + j + " = " + i * j);
				if (i == j) {
					break;
				}
			}
			System.out.println("===============");
		}
		// (2) break 이용하지 않기.
		System.out.println("\n\n\n(2) break 이용하지 않기");
		for (int i = 2; i < 10; i++) {
			System.out.println("===== " + i + "단 =====");
			for (int j = 1; j <= i; j++) {
				System.out.println(i + " x " + j + " = " + i * j);
			}
			System.out.println("===============");
		}
		
	}

}
