package com.itwill.class05;

public class Subject {
	// field
	int korean;
	int english;
	int math;
	int science;

	// TODO 생성자: (1)기본 생성자. (2)아규먼트를 갖는 생성자.
	public Subject() {
	}

	public Subject(int korean, int english, int math, int science) {
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.science = science;
	}

	// TODO 메서드: (1)총점 리턴. (2) 평균 리턴.
	public int sum() {
		int result = korean + english + math + science;
		return result;
	}

	public double avg() {
		double result = (double) sum() / 4;
		return result;
	}
}
