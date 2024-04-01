package com.itwill.inheritance01;

public class InheritanceMain01 {

	public static void main(String[] args) {
		BassicTv tv = new BassicTv(true, 1, 1);
		tv.powerOnOff();
		System.out.println(tv.getChannel());
		System.out.println(tv.channelUp());
		System.out.println(tv.channelUp());
		System.out.println(tv.channelUp());
		tv.powerOnOff();
		System.out.println(tv.channelUp());

		SmartTv tv2 = new SmartTv();
		tv2.powerOnOff(); // 상위클래스에서 상속받은 메서드를 호출
		tv2.webBrowsing();
		System.out.println(tv2.channelUp());
		System.out.println(tv2.channelDown());
		System.out.println(tv2.channelUp());
	}

}
