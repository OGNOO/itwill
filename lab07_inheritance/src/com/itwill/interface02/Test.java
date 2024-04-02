package com.itwill.interface02;

// 인터페이스가 가질 수 있는 멤버들 
// <Java 7 이하>===================================================================
// 1. [public static final] 필드
// 2. [public abstract] 메서드

// <Java 8>========================================================================
// 3. [public] static 메서드
//   -> 객체 생성 없이, 인터페이스 이름을 접두사로 써서 호출할 수 있는 메서드
// 4. [public] default 메서드
//   -> body(몸통)이 구현된 메서드
//   -> 인터페이스를 구현한 하위 타입의 객체를 생성한 후에 호출할 수 있는 메서드

// <Java 9>========================================================================
// 5. private static 메서드
//   -> body 가 구현된 메서드
//   -> static 또는 default 메서드에서만 호출할 목적으로 만드는 메서드
// 6. private 메서드
//   -> body 가 구현된 메서드
//   -> default 메서드에서만 호출할 목적으로 만드는 메서드

public interface Test {
	// 1. [public static final] 필드
	int VERSION = 1;

	// 2. [public abstract] 메서드
	void test();

	// 3. [public] static 메서드
	static void staticMethod() {
		System.out.println("공개 정적 메서드");
		privateStaticMethod(); // -> 코드가 너무 길고 복잡할때 이런식으로 사용
		System.out.println("==========================================");
	}

	// 4. [public] default 메서드
	default void defaultMethod() {
		System.out.println("공개 기본(public default) 메서드");
		privateStaticMethod(); // -> 코드가 너무 길고 복잡할때 이런식으로 사용
		privateMethod(); // -> 코드가 너무 길고 복잡할때 이런식으로 사용(static 이 아닐때 사용할 용도)
		System.out.println("==========================================");
	}

	// 5. private static 메서드
	private static void privateStaticMethod() {
		System.out.println("비공개 정적 메서드");
	}

	// 6. private 메서드
	private void privateMethod() {
		System.out.println("비공개 구현된 메서드");
	}
}
