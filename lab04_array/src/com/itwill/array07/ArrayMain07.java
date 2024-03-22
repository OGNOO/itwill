package com.itwill.array07;

public class ArrayMain07 {

	public static void main(String[] args) {
		// 문자열 1차원 배열
		String leSserafim[] = { "채원", "사쿠라", "윤진", "카즈하", "은채", };
		String newJeans[] = { "혜인", "혜린", "민지", "하니", "다니엘", };
		String bts[] = { "정국", "뷔", "RM", "지민", "슈가", "제이홉", "진", };

		// 문자열 2차원 배열
		String[][] idols = { leSserafim, newJeans, bts };

		// 인덱스를 사용한 2차원 배열 원소 출력
		for (int i = 0; i < idols.length; i++) {
			for (int j = 0; j < idols[i].length; j++) {
				System.out.print(idols[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("=====================================================");
		// 향상된 for 구문을 사용한 2차원 배열 원소 출력
		for (String[] is : idols) {
			for (String is2 : is) {
				System.out.print(is2 + "\t");
			}
			System.out.println();
		}
	}

}
