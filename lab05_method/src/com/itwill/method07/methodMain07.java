package com.itwill.method07;

public class methodMain07 {

	public static void main(String[] args) {
		// 가변길이 인수(variable-length argument):
		// 메서드의 아규먼트 개수가 미리 정해져 있지 않은 경우
		// 메서드를 호출할 때마다 아규먼트 개수가 달라질 수 있음
		System.out.printf("Hell\n"); // argument 1개
		System.out.printf("안녕하세여, %s\n", "오쌤"); // argument 2개
		System.out.printf("%d x %d = %d\n", 2, 3, 2 * 3); // argument 4개

		int total = addAll();
		System.out.println("total = " + total);

		total = addAll(1, 100);
		System.out.println("total = " + total);

		total = addAll(1, 3, 5);
		System.out.println("total = " + total);

		System.out.println("\n===============\n" + calculate("z"));
		System.out.println("\n===============\n" + calculate("+"));
		System.out.println("\n===============\n" + calculate("+", 1, 2, 3, 4, 5));
		System.out.println("\n===============\n" + calculate("*", 1, 2, 3, 4, 5));
	}

	// 가변길이 인수를 갖는 메서드를 선언하는 방법:
	// 가변길이 인수를 저장하는 파라미터는 "변수타입...변수이름" 형식으로 선언
	// 가변길이 인수는 메서드 안에서 배열처럼 취급
	// - 가변길이 인수는 length 속성을 가지고 있음
	// - for 문, 향상된 for 문에서 사용할 수 있음
	// 가변길이 인수를 저장하는 파라미터는 반드시 마지막 파라미터야야 함!
	// 가변길이 인수를 저장하는 파라미터는 1개만 있을 수 있음!
	// 가변길이 인수를 갖는 메서드를 호출할 때는 아규먼트 개수의 제한이 없음
	// - 아규먼트를 전달하지 않아도 됨

	public static int addAll(int... args) {
		System.out.println("length = " + args.length); // 가변길이 인수는 배열처럼 length 속성

		int result = 0;

		for (int i : args) {
			result += i;
		}
		return result;
	}

	/**
	 * calculate. op 의 값이 "+"인 경우에는, 가변길이 인수 values의 합을 리턴. values가 없으면 0을 리턴 op 의
	 * 값이 "*"인 경우에는, 가변길이 인수 values의 곱을 리턴. values가 없으면 1을 리턴 op 의 값이 "+"나 "*"가 아니면
	 * 0을 리턴
	 * 
	 * @param op     연산자. "+" 또는 "*"
	 * @param values 가변길이 인수. 정수(int)
	 * @return values 들의 합 또는 곱
	 */
	public static int calculate(String op, int... values) {
		int result = 0;

		if (op == "+") {
			for (int i = 0; i < values.length; i++) {
				result += values[i];
			}
		} else if (op == "*") {
			result = 1;
			for (int i = 0; i < values.length; i++) {
				result *= values[i];
			}
		} else {
			result = 0;
		}

		return result;
	}
}
