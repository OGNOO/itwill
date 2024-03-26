package com.itwill.class04;

public class Rectangle {
	// field
	double width;
	double heigth;

	// 생성자
	// (1) 기본 생성자
	public Rectangle() {
	}

	// (2) 가로/세로를 아규먼트로 전달받아서 초기화하는 생성자
	public Rectangle(double width, double heigth) {
		this.width = width;
		this.heigth = heigth;
	}

	// 메서드
	// (1) 넓이를 리턴
	public double area() {
		double rectangleArea = width * heigth;
		return rectangleArea;
	}

	// (2) 둘레길이를 리턴
	public double perimeter() {
		double rectanglePerimeter = (width + heigth) * 2;
		return rectanglePerimeter;
	}
}
