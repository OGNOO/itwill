package com.itwill.inheritance02;

public class Person {
	// 필드
	private String name;

	// constructor
	public Person() {
		System.out.println("Person() 생성자");
	}

	public Person(String name) {
		this.name = name;
		System.out.println("Person(name) 생성자");
	}

	// method
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
