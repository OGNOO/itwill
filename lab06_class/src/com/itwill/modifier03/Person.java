package com.itwill.modifier03;

public class Person {
	private String name; // 읽기 전용 필드
	private int age;

	// constructor
	public Person(String name, int age) {
		if (name != null && age >= 0) {
			this.name = name;
			this.age = age;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// method
	// getter 메서드
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	// setter 메서드
	public void setAge(int age) {
		if (age >= 0) {
			this.age = age;
		}
	}
}
