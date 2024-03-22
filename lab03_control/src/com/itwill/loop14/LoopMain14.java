package com.itwill.loop14;

import java.util.Scanner;

public class LoopMain14 {

	public static void main(String[] args) {
		// 교재 연습문제 7
		boolean run = true; // 프로그램을 계속 실행할 지, 종료할 지를 결정하기 위한 변수
		int balance = 0; // 은행 계좌의 잔고를 저장하기 위한 변수
		Scanner scanner = new Scanner(System.in); // 콘솔 입력을 위해서

		int moneyIn = 0;
		int moneyOut = 0;

		while (run) {
			System.out.println("==========================================");
			System.out.println("1. 예금 | 2. 출금 | 3. 잔고 | 4. 종료");
			System.out.println("==========================================");
			System.out.print("선택> ");

			int user = scanner.nextInt();
			scanner.nextLine();

			switch (user) {
			case 1:
				System.out.print("예금액>");
				moneyIn = scanner.nextInt();
				scanner.nextLine();
				balance += moneyIn;
				break;

			case 2:
				if (balance == 0) {
					System.out.println("잔고가 0원이므로 초기 화면으로 돌아갑니다.");
					break;
				}
				System.out.print("출금액>");
				moneyOut = scanner.nextInt();
				scanner.nextLine();

				if (balance >= moneyOut) {
					balance -= moneyOut;
				} else {
					System.out.printf("현재 잔고는 %d원 입니다. %d원 보다 적은 금액을 입력해주세요\n", balance, balance);
				}
				break;

			case 3:
				System.out.print("잔고>");
				System.out.println(balance);
				break;

			case 4:
				System.out.println();
				return;

			default:
				System.out.println("잘못된 입력입니다. 다시 선택해주세요");

			}

		}

		System.out.println("프로그램 종료");
		scanner.close();
	}

}
