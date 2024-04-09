package com.itwill.map03;

import java.util.HashMap;

public class MapMain03 {

	public static void main(String[] args) {
		// 단어 개수 세기(word counting):
		// 문자열에 등장하는 단어를 Key 로 하고,
		// 단어가 문자열에 등장하는 횟수를 Value 로 하는 Map 객체를 만들고 출력

//		String sentence = "짜장면 짬뽕 볶음밥 김치찌개 부대찌개 김치찌개 된장찌개";
		// -> 결과: {짜장면=2, 짬뽕=1, 볶음밥=1, 김치찌개=2, 부대찌개=1, 된장찌개=1}
		String sentence = "짜장면 짬뽕 짜장면 짬뽕";

		// String[]
		// Map<String, Integer>
		String[] words = sentence.split(" ");
		for (String w : words) {
			System.out.println(w + " ");
		}
		System.out.println();

		HashMap<String, Integer> wordCount = new HashMap<>();
		System.out.println(wordCount);

		for (String w : words) {
			Integer count = wordCount.getOrDefault(w, 0);
			wordCount.put(w, count + 1);
			System.out.println(wordCount);
		}

		System.out.println("===================================");
		HashMap<String, Integer> wordCount2 = new HashMap<>();
		for (String w : words) {
			Integer count = wordCount2.get(w);
			if (count != null) {
				wordCount2.put(w, count + 1);
			} else {
				wordCount2.put(w, 1);
			}
			System.out.println(wordCount2);
		}
		System.out.println("===================================");
		HashMap<String, Integer> wordCount3 = new HashMap<>();
		for (String w : words) {
			if (wordCount3.containsKey(w)) {
				wordCount3.put(w, wordCount3.get(w) + 1);
			} else {
				wordCount3.put(w, 1);
			}
		}
		System.out.println(wordCount3);
	}
}
