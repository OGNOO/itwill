package com.itwill.interface02;

public class InterfaceMain02 {

	public static void main(String[] args) {
		System.out.println(Test.VERSION);
		Test.staticMethod();

		// Test 인터페이스의 구현 객체 생성 & 메서드 호출
		Test t = new TestImpl(); // 다형성: SuperType var = new SubType();
		t.defaultMethod();
		t.test();
	}
}
