package com.itwill.loop12;

import java.util.Random;

public class LoopMain12 {

	public static void main(String[] args) {
		// 교재 연습문제 3
		// 주사위 2개를 던졌을 때 결과를 (x, y) 형식으로 출력
		// 주사위 2개의 눈의 합이 5가 되면 실행을 멈춤
		// (1, 2), (6, 2), ..., (2, 3)
		Random rand = new Random();

		while (true) {
			int dice1 = rand.nextInt(1, 7);
			int dice2 = rand.nextInt(1, 7);
			System.out.printf("( %d, %d ) \n", dice1, dice2);
			if (dice1 + dice2 == 5) {
				break;
			}
		}
		System.out.println();
		// 교재 연습문제 4
		// 4x + 5y = 60 방정식을 만족하는 x와 y를 찾기
		// 단, x와 y는 10 이하의 자연수 (1, 2, 3 ,... , 9, 10)
		// (x = 5, y = 8), 

		int z = 60;
		for (int x = 1; x < 11; x++) {
			if (z % x == 0 && x % 5 == 0) {
				int y = (z - 4 * x) / 5;
				System.out.printf("( x = %d, y = %d ) ", x, y);
			}
		}
	}

}
