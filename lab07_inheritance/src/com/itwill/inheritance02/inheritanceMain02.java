package com.itwill.inheritance02;

public class inheritanceMain02 {
	public static void main(String[] args) {
		// BusinessPerson 타입 객체 생성:
		BusinessPerson person1 = new BusinessPerson();
		System.out.println(person1.getName());
		System.out.println(person1.getCompany());

		BusinessPerson person2 = new BusinessPerson("아이티윌");
		System.out.println(person2.getName());
		System.out.println(person2.getCompany());

		BusinessPerson person3 = new BusinessPerson("오쌤", "아이티윌");
		System.out.println(person3.getName());
		System.out.println(person3.getCompany());
	}
}
