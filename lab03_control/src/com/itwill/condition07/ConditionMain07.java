package com.itwill.condition07;

import java.util.Random;

public class ConditionMain07 {

	public static void main(String[] args) {
		// 조건문, 비교/논리 연산자 연습

		// 난수(random number)를 생성할 수 있는 타입의 변수를 선언, 초기화
		Random rand = new Random();

		// rand.nextInt(x): 0 이상 x 미만의 정수 난수를 반환
		// rand.nextInt(x, y): x 이상 y 미만의 정수 난수를 반환

		// 세 과목이 모두 40점 이상이고, 평균이 60점 이상이면 "합격",
		// 그렇지 않으면 "불합격"을 출력

		int java = rand.nextInt(101);
		int sql = rand.nextInt(101);
		int javaScript = rand.nextInt(101);

		boolean passMinScore = java >= 40 && sql >= 40 && javaScript >= 40;
		boolean avg = (java + sql + javaScript) / 3 >= 60;

		System.out.println("java: " + java);
		System.out.println("sql: " + sql);
		System.out.println("javaScript: " + javaScript);

		if (passMinScore && avg) {
			System.out.println("합격 입니다.");
		} else {
			System.out.println("불합격 입니다.");

		}

	}

}
