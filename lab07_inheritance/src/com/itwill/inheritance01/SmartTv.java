package com.itwill.inheritance01;

// 스마트TV는 기본TV를 확장(상속)한다
// 상위(super) 클래스, 부모(parent), 기본(base) 클래스 - BassicTv
// 하위(sub) 클래스, 자식(child), 유도(derived) 클래스 - SmartTv
// 모든 클래스의 최상위 클래스는 java.lang.Object 클래스
// 상위 클래스가 java.lang.Object 인 경우 extends Object 는 생략 가능
public class SmartTv extends BassicTv {
	// 필드
	@SuppressWarnings("unused")
	private String ip;

	// method
	public void webBrowsing() {
		System.out.println("인터넷 연결");
	}

	public void checkChannel() {
		System.out.println(getChannel());
	}
}
