package com.itwill.class05;

import java.util.Scanner;

public class ClassMain05 {

	public static void main(String[] args) {
		// TODO: Subject, Student 클래스 객체 생성과 메서드 호출 결과 테스트.
		Scanner scanner = new Scanner(System.in);
		System.out.println("번호와 이름을 입력하세요>> ");
		int num = scanner.nextInt();
		String name = scanner.next();
		scanner.nextLine();
		System.out.println("국어 영어 수학 과학 점수를 차례대로 입력하세요>> ");
		int kor = scanner.nextInt();
		int eng = scanner.nextInt();
		int math = scanner.nextInt();
		int sci = scanner.nextInt();
		scanner.nextLine();

		Subject subject = new Subject(kor, eng, math, sci);
		Student student = new Student(num, name, subject);

		student.info();

		// null 값
		System.out.println("====================");
		Student $student = new Student();

		$student.info();

		System.out.println("====================");
		Student $$student = new Student(num, name, kor, eng, math, sci);

		$$student.info();

		scanner.close();
	}

}
