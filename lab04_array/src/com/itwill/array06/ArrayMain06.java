package com.itwill.array06;

public class ArrayMain06 {

	public static void main(String[] args) {
		// 다차원 배열: 배열들을 원소로 갖는 배열
		// 2차원 배열: 1차원 배열들을 원소로 갖는 배열
		// - 2차원 배열은 인덱스를 2개를 갖음

		// 2차원 배열 선언 & 초기화 방법 1:
		// 타입[][] 변수이름 = new 타입[][]{ { ... } , { ... } , ... };
		// 타입[][] 변수이름 = { { ... } , { ... } , ... };
		int array[][] = { { 1, 2, 3, 4 }, { 5, 6, 7 }, { 8, 9, 10, 11, 12 }, };
		System.out.println(array); // ==> array의: [[I@메모리주소
		System.out.println(array.length); // ==> 2차원 배열의 원소: 1차원 배열. 1차원 배열 개수:
		System.out.println(array[0]); // ==> array[0]의: [[I@메모리주소
		System.out.println(array[0].length);

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + "\t");
			}
			System.out.println();
		}

	}

}
