package com.itwill.inheritance08;

public class Circle extends Shape {
	// field
	private double radius;

	// constructor
	public Circle(double radius) {
		super("원");
		this.radius = radius;
	}

	// method
	@Override
	public double area() {
		return radius * radius * Math.PI;
	}

	@Override
	public double perimeter() {
		return radius * Math.PI * Math.PI;
	}

}
