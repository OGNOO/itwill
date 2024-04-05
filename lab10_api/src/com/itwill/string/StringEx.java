package com.itwill.string;

public class StringEx {
	public static void main(String[] args) {
		// java.lang.String 클래스의 메소드 사용법

		// ex1. 주민번호에서 성별을 표시하는 위치의 문자열 찾기
		System.out.println("\n======================================================1");
		String ssn = "980421-1234567";
		System.out.println(ssn.charAt(7));

		// ex2. 문자열 자르기
		System.out.println("\n======================================================2");
		String date = "2024-08-05";
		String[] dateArray = date.split("-");
		for (String string : dateArray) {
			System.out.print(string);
		}

		// ex3. 아래의 문자열 배열에서 5글자 이상인 문자열들만 출력.
		// 결과: JavaScript Servlet Spring
		System.out.println("\n======================================================3");
		String[] languages = { "Java", "SQL", "JavaScript", "HTML", "CSS", "Servlet", "Spring" };
		for (String string : languages) {
			if (string.length() >= 5) {
				System.out.println(string);
			}
		}

		// ex4. 아래의 문자열 배열에서 "홍길동" 문자열이 처음 등장하는 인덱스를 출력.
		// 만약에 "홍길동"이 배열에 없는 경우에는 -1을 출력.
		// 결과: 2
		System.out.println("\n======================================================4");
		String[] names = { "오쌤", "John", "홍길동", "Gildong", "홍길동" };

		int index = -1;
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals("홍길동")) {
				index = i;
				break;
			}
		}
		System.out.println(index);

		// ex5. 아래의 문자열 배열에서 대소문자 구별없이 "est"를 포함하는 문자열들을 출력.
		// 결과: TEST test TeSt tEsT
		System.out.println("\n======================================================5");
		String[] tests = { "TEST", "test", "TeSt", "tEsT", "테스트" };

		for (int i = 0; i < tests.length; i++) {
			if (tests[i].toLowerCase().contains("est")) {
				System.out.println(tests[i]);
			}

		}

		// ex6. 아래의 "YYYY-MM-DD" 형식의 문자열에서 년/월/일 정보를 "int" 타입 변수에 저장하고 출력.
		// 결과: year=2023, month=10, day=4
		System.out.println("\n======================================================6");
		String today = "2024-04-05";

		String[] todayArray = today.split("-");
		int year = Integer.parseInt(todayArray[0]);
		int month = Integer.parseInt(todayArray[1]);
		int day = Integer.parseInt(todayArray[2]);
		System.out.println("year = " + year + ", month = " + month + ", day = " + day);

	}
}
