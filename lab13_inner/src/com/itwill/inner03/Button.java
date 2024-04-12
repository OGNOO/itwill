package com.itwill.inner03;

public class Button {
	// public static 내부 인터페이스 -> static 은 생략 가능
	public interface OnClickListener {
		void onClick(); // public abstract 메서드
	}

	// field
	private String btnName;
	private OnClickListener listener;

	// constructor
	public Button(String btnName) {
		this.btnName = btnName;
	}

	// getterSetter
	public void setOnClickListener(OnClickListener listener) {
		this.listener = listener;
	}

	// method
	public void click() {
		System.out.println(btnName + "버튼: ");
		listener.onClick();
	}
}