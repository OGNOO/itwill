package com.itwill.inheritance06;

import java.util.Objects;

@SuppressWarnings("unused")
public class User {
	// field
	private String id;
	private String password;

	// constructor
	public User() {
	}

	public User(String id, String password) {
		this.id = id;
		this.password = password;
	}

	// method

	// toString 재정의 - id 와 password 를 출력
	@Override
	public String toString() {
		return "ID = " + id + ", PW = " + password;
	}

	// equals 재정의 - id 가 같으면 같은 객체, 그렇지 않으면 다른 객체
//	@Override
//	public boolean equals(Object obj) { // -> 이클립스에서 생성한 equals() @Override 메서드
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		return Objects.equals(id, other.id);
//	}

//	@Override
//	public boolean equals(Object obj) {
//		boolean result = false;
//
//		if (obj instanceof User) {
//			User user = (User) obj;
//			result = (this.id == user.id);
//		}
//		return result;
//	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof User && id != null) {
			User other = (User) obj;
			result = id.equals(other.id);
		}
		return result;
	}

	// hashCode 재정의 - ? id 가 같은 User 객체는 같은 정수값이 리턴되도록
//	@Override
//	public int hashCode() { // -> 이클립스에서 생성한 hashCode() @Override 메서드
//		return Objects.hash(id);
//	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			result += (int) ch;
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
