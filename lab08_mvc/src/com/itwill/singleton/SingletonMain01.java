package com.itwill.singleton;

public class SingletonMain01 {

	public static void main(String[] args) {

		Captain captain = Captain.getInstance();

		System.out.println(captain);
		System.out.println(captain.getName());

		Captain captain2 = Captain.getInstance();
		System.out.println(captain2);

		captain2.setName("아이언맨");
		System.out.println(captain.getName());
		System.out.println(captain2.getName());

		Singleton s1 = Singleton.INSTANCE;
		System.out.println(s1); // enum 타입은 toString() 메서드를 override
		System.out.println(s1.getName());
		s1.setName("블랙위도우");
		System.out.println(s1.getName());

	}
}
