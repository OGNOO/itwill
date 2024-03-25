package com.itwill.method05;

public class MethodMain05 {

	public static void main(String[] args) {
		int array[] = { 1, 2, 7, 4, 5 };
		int arrSum = sum(array);
		double arrAvg = mean(array);
		int arrMax = max(array);
		int arrMin = min(array);
		System.out.println(arrSum);
		System.out.println(arrAvg);
		System.out.println(arrMax);
		System.out.println(arrMin);
	}

	/**
	 * sum. 아규먼트로 전달받은 정수들의 1차원 배열의 모든 원소들의 합을 리턴
	 * 
	 * @param array 정수들의 1차원 배열(int[])
	 * @return array의 모든 원소들의 합
	 */
	public static int sum(int[] arr) {
		int arrSum = 0;
//		for (int i = 0; i < arr.length; i++) {
//			arrSum += arr[i];
//		}
		for (int i : arr) {
			arrSum += i;
		}
		return arrSum;
	}

	/**
	 * mean. 아규먼트로 전달받은 정수들의 1차원 배열 원소들의 평균을 리턴
	 * 
	 * @param array 정수들의 1차원 배열
	 * @return array 원소들의 평균을 double 타입으로 리턴
	 */
	public static double mean(int[] arr) {
		double arrAvg = (double) sum(arr) / arr.length;

		return arrAvg;
	}

	/**
	 * max. 아규먼트로 전달받은 정수들의 1차원 배열 원소들 중 최댓값을 리턴
	 * 
	 * @param array 정수들의 1차원 배열
	 * @return array 원소들 중 최댓값
	 */
	public static int max(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		int arrMax = arr[arr.length - 1];
		return arrMax;
	}

	/**
	 * min. 아규먼트로 전달받은 정수들의 1차원 1차원 배열 원소들 중 최솟값을 리턴
	 * 
	 * @param array 정수들의 1차원 배열
	 * @return array 원소들 중 최솟값
	 */
	public static int min(int[] arr) {
		int result = arr[0];
		for (int x : arr) {
			if (x < result) {
				result = x;
			}
		}

		return result;
	}

}
