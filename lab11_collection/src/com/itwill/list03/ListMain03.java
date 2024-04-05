package com.itwill.list03;

import java.util.ArrayList;
import java.util.Random;

public class ListMain03 {

	public static void main(String[] args) {
		// 1. 정수를 저장할 수 있는 ArrayList를 선언(numbers), 객체 생성
		ArrayList<Integer> numbers = new ArrayList<Integer>();

		// 2. numbers 에 [0, 100] 범위(0 이상 100 미만) 의 난수 20개를 저장
		Random random = new Random(100);
		for (int i = 0; i < 20; i++) {
			int randomNumber = random.nextInt(100);
			numbers.add(randomNumber);
		}

		// 3. numbers 출력
		System.out.println(numbers);

		// 4. 홀수들만 저장할 수 있는 ArrayList 를 선언(odds), 객체 생성
		ArrayList<Integer> odds = new ArrayList<Integer>();

		// 5. numbers 에 저장된 숫자들 중에서 홀수들을 찾아서 odds 에 저장
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) % 2 == 1) {
				odds.add(numbers.get(i));
			}
		}
		// 6. odds 출력
		System.out.println(odds);

		// 7. 정수를 저장할 수 있는 ArrayList를 선언(evenSquares), 객체 생성
		ArrayList<Integer> evenSquares = new ArrayList<Integer>();

		// 8. numbers 에 저장된 숫자들 중에서 짝수를 찾아서 짝수의 제곱을 evenSquares 에 저장
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) % 2 == 0) {
				evenSquares.add(numbers.get(i) * numbers.get(i));
			}
		}

		// 9. evenSquares 출력
		System.out.println(evenSquares);

		// 10. List에서 List 빼기
		numbers.removeAll(odds);
		System.out.println(numbers);
		ArrayList<Integer> evenSquares2 = new ArrayList<>();
		for (int i = 0; i < numbers.size(); i++) {
			evenSquares2.add(numbers.get(i) * numbers.get(i));
		}
		System.out.println(evenSquares2);
	}

}
