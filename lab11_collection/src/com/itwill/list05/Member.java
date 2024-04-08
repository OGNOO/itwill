package com.itwill.list05;

import java.util.Objects;

public class Member {
	// field
	private String id;
	private String password;

	// constructor
	public Member(String id, String password) {
		this.id = id;
		this.password = password;
	}

	// method
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id);
	}

	// getterSetter
	@Override
	public String toString() {
		return "Member [id = " + id + ", password = " + password + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
