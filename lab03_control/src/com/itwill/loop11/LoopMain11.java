package com.itwill.loop11;

public class LoopMain11 {

	public static void main(String[] args) {
		// Ex1. 1부터 10까지 자연수들의 합
		int sum = 0;
		for (int i = 1; i < 11; i++) {
			sum += i;
		}
		System.out.println(sum);
		// Ex2. 1부터 100까지 자연수들 중에서 짝수들의 합을 계산하고 출력
		sum = 0;
		for (int i = 1; i < 101; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		System.out.println(sum);
		// Ex2. 1부터 100까지 자연수들 중에서 홀수들의 합을 계산하고 출력
		sum = 0;
		for (int i = 1; i < 101; i += 2) {
			sum += i;
		}
		System.out.println(sum);
		//
		sum = 0;
		int i = 0;
		while (i < 101) {
			if (i % 2 == 1) {
				sum += i;
			}
			i++;
		}
		System.out.println(sum);
		
	}

}
