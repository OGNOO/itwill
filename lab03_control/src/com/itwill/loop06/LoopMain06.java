package com.itwill.loop06;

public class LoopMain06 {

	public static void main(String[] args) {
		// 중첩 반복문: 반복문 안에서 반복문을 사용
		for (int i = 2; i < 10; i++) {
			System.out.println("\t===== "+i+"단 =====");
			for (int j = 1; j < 10; j++) {
				System.out.println("\t"+i + " * " + j + " = " + i * j);

			}
			System.out.println("\t===============\n");
		}
		for (int i = 0; i < 5; i++) {
			  for (int j = 0; j <= i; j++) {
			    System.out.print("*");
			  }
			  System.out.println();
			}
	}

}
