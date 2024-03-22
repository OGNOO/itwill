package com.itwill.loop08;

import java.util.Scanner;

public class LoopMain08 {

	public static void main(String[] args) {
		// while 반복문

		System.out.println("===== [1] =====");
		int i = 1;
		while (i <= 5) {
			System.out.print(i + " ");
			i++; // i += 1; i = i + 1;
		}
		System.out.println();

		System.out.println("===== [2] =====");
		// 5 4 3 2 1 출력
		int j = 5;
		while (j > 0) {
			System.out.print(j + " ");
			j--;

		}
		System.out.println();

		// 1 3 5 7 9
		int k = 1;
		System.out.println("===== [3] =====");
		while (k < 10) {
			System.out.print(k + " ");
			k += 2;

		}
		System.out.println();

		// 1 3 5 7 9
		int l = 1;
		System.out.println("===== [4] =====");
		while (l < 10) {
			if (l % 2 == 1) {
				System.out.print(l + " ");
			}
			l++;

		}
		System.out.println();

		// while문을 사용해서 구구단을 9단까지 출력
		System.out.print("\n몇단까지 출력? >>>");
		Scanner scanner = new Scanner(System.in);
		
		int m = 2;
		int n = 2;
		int maxDan = scanner.nextInt()+1;
		System.out.println("===== [5] =====");
		while (m < maxDan && n < 10) {
			System.out.println(m + " x " + n + " = " + m * n);

			n++;
			if (n == 10) {
				n = 2;
				m++;
				System.out.println();
			}
		}
		scanner.close();
	}

}
