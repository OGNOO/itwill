package com.itwill.class04;

public class ClassMain04 {

	public static void main(String[] args) {
		// 기본 생성자를 사용해서 Rectangle 객체 생성, 메소드 호출
		Rectangle rectangle = new Rectangle(2.5, 3.1);
		System.out.printf("사각형 넓이: %.3f\n", rectangle.area());
		System.out.println("사각형 둘레: " + rectangle.perimeter());
	}

}
