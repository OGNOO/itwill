package com.itwill.singleton;

// 싱글톤(singleton): 힙에 오직 한 개만 존재하는 객체
// 싱글톤 디자인 패턴(singleton design pattern):
// 클래스를 설계할 때 객체를 오직 하나만 생성할 수 있도록 작성하는 디자인 패턴
// (1) private static field
// (2) private constructor(생성자)
// (3) public static method

public class Captain {
	// field
	private static Captain instance = null;

	private String name;

	// constructor
	private Captain() {
		name = "캡틴 아메리카";
	}

	// method
	public static Captain getInstance() {
		if (instance == null) {
			instance = new Captain();
		}

		return instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
