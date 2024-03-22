package com.itwill.array05;

import java.util.Random;

public class ArrayMain05 {

	public static void main(String[] args) {
		// 과제:
		// 정수(int) 5개를 저장할 수 있는 배열을 선언하고, 기본값으로 초기화.
		// 배열에 0 이상 10 이하 난수 5개를 저장.
		// 배열 원소들 중 최댓값을 찾아서 출력.
		// 배열 원소들 중 최솟값을 찾아서 출력.

		Random random = new Random();

		int scores[] = new int[5];

		for (int i = 0; i < scores.length; i++) {
			scores[i] = random.nextInt(11);
		}
		System.out.print("배열 랜덤 값: ");

		for (int i : scores) {
			System.out.printf(" %2d ", i);
		}

		for (int i = 0; i < scores.length - 1; i++) {
			System.out.print("\n" + (i + 1) + " 회차:");
			for (int j = 0; j < scores.length - i - 1; j++) {
				if (scores[j] > scores[j + 1]) {
					int temp = scores[j];
					scores[j] = scores[j + 1];
					scores[j + 1] = temp;
				}
				for (int score : scores) {
					System.out.printf(" %2d ", score);
				}
				System.out.print(" ==> ");
			}
		}
		System.out.print("\n\n배열 정렬 값: ");

		for (int score : scores) {
			System.out.printf(" %2d ", score);
		}
		int max = scores[scores.length - 1];
		int min = scores[0];

		System.out.println();
		System.out.println("최대값: " + max);
		System.out.println("최솟값: " + min);
	}

}
