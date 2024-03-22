package com.itwill.condition02; // 패키지 선언문

import java.util.InputMismatchException;
import java.util.Scanner; // import 문장

public class ConditionMain02 {

	public static void main(String[] args) {
		// 과제
		// 콘솔 창에서 정수 1개를 입력받고, int 타입 변수에 저장
		// 만약 입력받은 정수가 0보다 크면, "positive"라고 출력
		// 0이면, "zero"라고 출력
		// 그렇지 않으면, "negative"라고 출력

		// 콘솔 창에서 키보드 입력을 저장하는 방법:
		// (1) Scanner 타입의 변수를 선언하고, Scanner 객체를 저장
		Scanner scanner = new Scanner(System.in); // 입력 도구를 만들어서 변수에 저장.
		int inputData;
		int count = 0;
		while (true) {
			System.out.print("정수 1개를 입력하세요 : ");
			try {
				inputData = scanner.nextInt();
				scanner.close();

				break;
			} catch (InputMismatchException e) {
				count++;
				System.out.println("입력값이 유효하지 않습니다. 다시 입력하세요.");
				scanner.nextLine(); // 버퍼 지우기
				if (count == 5) {
					System.out.println("5회 틀리셨습니다. 프로그램을 종료합니다.");
					return;
				}
			}
		}

		System.out.println("입력받은 정수 = " + inputData);
		if (inputData > 0) {
			System.out.println("positive");
		} else if (inputData == 0) {
			System.out.println("zero");
		} else {
			System.out.println("negative");
		}

	}

}
