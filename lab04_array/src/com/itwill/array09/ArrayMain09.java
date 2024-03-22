package com.itwill.array09;

import java.util.Random;

public class ArrayMain09 {

	public static void main(String[] args) {
		Random random = new Random();

		// 1차원 정수(int) 배열 3개를 갖는 2차원 배열을 선언 & 초기화
		int arrary[][] = new int[3][]; // 1차원 배열 3개를 원소로 갖는 2차원 배열
		// 2차원 배열 array의 첫번째 배열에는 난수 2개, 두번째 배열에는 난수 3개,
		// 세번째 배열에는 난수 4개를 저장
		arrary[0] = new int[2];
		arrary[1] = new int[3];
		arrary[2] = new int[4];
		// 2차원 배열 array의 내용을 출력
		// 2차원 배열의 모든 원소들(정수 9개)의 합을 계산하고 출력
		// 2차원 배열의 모든 원소들(정수 9개)의 평균을 계산하고 출력
		
		int sum = 0;
		int count = 0;
		
		System.out.println("==== <forEach> ====");
		for (int row[] : arrary) {
			for (int column : row) {
				column = random.nextInt(11);
				sum += column;
				count++;
				System.out.printf("%2d | ", column);

			}
			System.out.println("\n===================");
		}
		double avg = (double) sum / count;
		System.out.println("합계: " + sum);
		System.out.printf("평균: %.2f\n", avg);
		
		System.out.println("\n====== <for> ======");
		int sum2 = 0;
		int count2 = 0;
		for (int i = 0; i < arrary.length; i++) {
			for (int j = 0; j < arrary[i].length; j++) {
				arrary[i][j] = random.nextInt(11);
				System.out.printf("%2d | ", arrary[i][j]);
				sum2 += arrary[i][j];
				count2++;
			}
			System.out.println("\n===================");
		}

		double avg2 = (double) sum2 / count2;
		System.out.println("합계: " + sum2);
		System.out.printf("평균: %.2f", avg2);

	}

}
