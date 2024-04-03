package com.itwill.interface03;

class A {
}

class B {
}

class C extends A { // 클래스 C 는 클래스 A 를 확장(상속)
}

//class D extends A , C { } // 다중 상속 - 자바는 클래스의 다중 상속을 허용하지 않음

interface I {
}

interface J {
}

class E implements I { // 클래스 E 는 인터페이스 I 를 구현
}

class F implements I, J { // 클래스가 구현하는 인터페이스의 개수는 제한이 없음
}

class G extends B implements I { // 클래스 G 는 클래스 B 를 확장하고, 인터페이스 I 를 구현
}

interface K extends I, J { // 인터페이스 K 는 인터페이스 I 와 J 를 확장, 인터페이스는 다중 상속 가능
}

//interface L extends A { } // 인터페이스는 클래스를 확장할 수 없음

interface Clickable {
	void click(); // public abstract
}

interface Scrollable {
	void scroll(); // public abstract
}

class Mouse implements Clickable, Scrollable {

	@Override
	public void click() {
		System.out.println("마우스 클릭");
	}

	@Override
	public void scroll() {
		System.out.println("마우스 휠 스크롤");
	}
}

public class InterfaceMain03 {

	public static void main(String[] args) {
		Mouse m1 = new Mouse();
		m1.click();
		m1.scroll();
		Clickable m2 = new Mouse();
		m2.click();
		((Scrollable) m2).scroll();
		((Mouse) m2).scroll();
	}

}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//