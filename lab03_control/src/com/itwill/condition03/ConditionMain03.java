package com.itwill.condition03;

import java.util.Scanner;

public class ConditionMain03 {

	public static void main(String[] args) {
		// Scanner 타입의 변수를 선언, 초기화

		// 콘솔 창에서 Java 과목 점수(0 ~ 100 정수)를 입력받고 저장

		// 콘솔 창에서 Sql 과목 점수(0 ~ 100 정수)를 입력받고 저장

		// 콘솔 창에서 JavaScript 과목 점수(0 ~ 100 정수)를 입력받고 저장

		// 세 과목의 총점(0 ~ 300 정수)을 계산하고 출력

		// 세 과목의 평균(float)을 계산하고 출력

		// 세 과목 평균이 90점 이상이면, "A"
		// 세 과목 평균이 80점 이상이면, "B"
		// 세 과목 평균이 70점 이상이면, "C"
		// 세 과목 평균이 70점 미만이면, "F " 라고 출력

		Scanner scanner = new Scanner(System.in);
		int Java, Sql, JavaScript;
		do {
			System.out.print("Java 점수를 입력하세요: ");
			Java = scanner.nextInt();
			System.out.print("Sql 점수를 입력하세요: ");
			Sql = scanner.nextInt();
			System.out.print("JavaScript 점수를 입력하세요: ");
			JavaScript = scanner.nextInt();

		} while (Java < 0 || Sql < 0 || JavaScript < 0);
		scanner.close();

		int sum = Java + Sql + JavaScript;
		float avg = (float) sum / 3;

		System.out.println("Java 점수: " + Java + ", Sql 점수: " + Sql + ", JavaScript 점수: " + JavaScript + "입니다.");
		System.out.printf("세 과목의 총점은  %d  입니다.\n", sum);
		System.out.printf("세 과목의 평균은 %.2f 입니다.\n", avg);

		System.out.print("등급은 ");
		if (avg >= 90) {
			System.out.print("A");
		} else if (avg >= 80) {
			System.out.print("B");
		} else if (avg >= 70) {
			System.out.print("C");
		} else
			System.out.print("F");

		System.out.println(" 입니다.");

	}

}
