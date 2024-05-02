package com.itwill.order.model;

public class Manager {
	public static final class Entity {
		public static final String TBL_MANAGER_ACCOUNT = "MANAGER_ACCOUNT";
		public static final String COL_NAME = "NAME";
		public static final String COL_PASSWORD = "PASSWORD";
		public static final String COL_CRUD = "CRUD";
	}

	private String name;
	private String password;
	private String crud;

	public Manager() {
	}

	public Manager(String name, String password, String crud) {
		this.name = name;
		this.password = password;
		this.crud = crud;
	}

	public String getCrud() {
		return crud;
	}

	public void setCrud(String crud) {
		this.crud = crud;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Manager [name=" + name + ", password=" + password + ", crud=" + crud + "]";
	}
}
