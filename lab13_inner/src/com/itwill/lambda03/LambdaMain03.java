package com.itwill.lambda03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LambdaMain03 {

	public static void main(String[] args) {
		// Stream 클래스 & 메서드

		Random rand = new Random();

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			numbers.add(rand.nextInt(100));
		}
		System.out.println(numbers);

		// numbers 의 원소들 중에서 짝수들만 선택(필터링)한 새로운 리스트를 만들고 출력
		ArrayList<Integer> evens = new ArrayList<Integer>();
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) % 2 == 0) {
				evens.add(numbers.get(i));
			}
		}
		System.out.println(evens);

//		List<Integer> evens2 = numbers.stream().filter(new Predicate<Integer>() {
//			@Override
//			public boolean test(Integer t) {
//				return t % 2 == 0;
//			}
//		}).toList();
//		System.out.println(evens2);

		// Stream 을 사용해서, numbers 의 원소들 중에서 짝수들만 선택한 리스트를 만들고 출력
		List<Integer> evens2 = numbers.stream().filter(x -> x % 2 == 0).toList();
		System.out.println(evens2);

		// Stream 을 사용해서, numbers 의 원소들 중에서 홀수들만 선택한 리스트를 만들고 출력
		List<Integer> odds = numbers.stream().filter(x -> x % 2 == 1).toList();
		System.out.println(odds);

		// Stream 을 사용해서, numbers 의 원소들 중에서 제곱을 저장하는 리스트를 만들고 출력
		List<Integer> squares = numbers.stream().map(x -> x * x).toList();
		System.out.println(squares);

		// Stream 을 사용해서, numbers 의 원소들 중에서 홀수들의 제곱을 저장하는 리스트를 만들고 출력
		List<Integer> result = numbers.stream().filter(x -> x % 2 == 1).map(x -> x * x).toList();
		System.out.println(result);

		List<String> languages = Arrays.asList("java", "javascript", "python", "c", "kotlin", "swift");

		// languages 원소들 중 문자열 길이 5글자 이상인 원소들의 리스트
		List<String> result0 = languages.stream().filter(x -> x.length() >= 5).toList();
		System.out.println(result0);

		List<String> result1 = languages.stream()/* .map(x -> x.toUpperCase()) */
				.map(String::toUpperCase).toList();
		System.out.println(result1);

		List<String> result2 = languages.stream().filter(x -> x.length() >= 5).map(x -> x.toUpperCase()).toList();
		System.out.println(result2);

		// 람다: (파라미터 선언) -> 리턴값
		// 메서드 참조(method reference)를 사용한 람다 표현식
		// (1) 람다가 아규먼트 1개만 갖고,
		// 람다의 리턴값이 그 아규먼트에서 "파라미터가 없는 메서드"를 호출한 경우
		// x -> x.toUpperCase()
		// String::toUpperCase
		// (2) 람다가 아규먼트를 1개만 갖고,
		// 람다의 구현부가 메서드 1개 호출이고, 그 메서드가 람다의 아규먼트를 전달받는 경우:
		// languages.forEach(x -> System.out.println(x));
		// languages.forEach(System.out::println);

	}
}
