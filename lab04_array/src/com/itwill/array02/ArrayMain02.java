package com.itwill.array02;

public class ArrayMain02 {

	public static void main(String[] args) {
		// 배열 선언 & 초기화 방법2 :
		// 배열이 가지고 있는 원소들을 나열하면서 배열을 초기화
		// 타입[] 변수이름 = new 타입[] { ... }; -> (주의) 배열 원소 개수를 표기하지 않음!
//		int numbers[] = new int[] { 1, 3, 5, 7 }; 
		// 타입[] 변수이름 = { ... };
		int numbers[] = { 1, 3, 5, 7 }; // int[] numbers = { 1, 3, 5, 7 };
		for (int i = 0; i < numbers.length; i++) {
			System.out.printf("[%d] %d\n", i, numbers[i]);
		}

		// 향상된 for 문(enhanced for statment, for-each)
		// for (변수 선언 : 배열이름) { ... }
		// 배열의 원소들을 순서대로 반복(iteration)하면서 반복문 블록을 수행
		// 배열 원소의 값을 읽을 수는 있지만, 변경할 수는 없다
		for (int number : numbers) {
			System.out.println(number);
			number = 200;
		}

		// boolean 타입 5개를 저장하는 배열을 선언하고, 기본값(false)로 초기화
		boolean[] bools = new boolean[5];
		for (int i = 0; i < bools.length; i++) {
			System.out.print(bools[i] + " ");
		}
		System.out.println();

		bools[0] = true; // 배열 bools의 인덱스 0 위치의 원소를 true로 변경
		bools[4] = true;
		for (boolean bool : bools) {
			System.out.println(bool);
		}
		System.out.println();

		// 문자열(String) 3개를 저장할 수 있는 배열을 선언, 기본값(null)으로 초기화
		String names[] = new String[3];
		names[0] = "홍길동";
		names[2] = "오쌤";

		for (String name : names) {
			System.out.print(name + " ");
		}
		System.out.println();
	}

}
