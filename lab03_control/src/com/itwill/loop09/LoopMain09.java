package com.itwill.loop09;

import java.util.Scanner;

public class LoopMain09 {

	public static void main(String[] args) {
		// 중첩 while 문장을 사용해서 구구단
		System.out.print("\n몇단까지 출력? >>>");
		Scanner scanner = new Scanner(System.in);

		int firstNum = 1; // 첫번째 숫자가 몇부터 시작하는지?
		int secondNum = 2; // 두번째 숫자가 몇부터 시작하는지?
		int maxDan = scanner.nextInt() + 1; // 몇단까지 리밋을 걸건지?
		System.out.println("===== [5] =====");
		while (firstNum < maxDan && secondNum < maxDan) {
			System.out.println(firstNum + " x " + secondNum + " = " + firstNum * secondNum);
			secondNum++;
			
			if (secondNum == maxDan) {
				secondNum = 2;
				firstNum++;
				System.out.println();
			}
		}
		scanner.close();

	}

}
