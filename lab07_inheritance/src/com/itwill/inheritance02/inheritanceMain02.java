package com.itwill.inheritance02;

public class inheritanceMain02 {
	public static void main(String[] args) {
		// BusinessPerson 타입 객체 생성:
		BusinessPerson person1 = new BusinessPerson();
		System.out.println(person1.getName() + " : " + person1.getCompany());

		System.out.println("================================================");

		BusinessPerson person2 = new BusinessPerson("아이티윌");
		System.out.println(person2.getName() + " : " + person2.getCompany());

		System.out.println("================================================");

		BusinessPerson person3 = new BusinessPerson("오쌤", "아이티윌");
		System.out.println(person3.getName() + " : " + person3.getCompany());

		System.out.println("================================================");

	}
}
