package com.itwill.file06;

import java.io.Serializable;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// field
	private int id;
	private String name;
	private Score score;

	// constructor
	public Student() {
	}

	public Student(int id, String name, Score score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	// method
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", " + score + "]";
	}

	// getterSetter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
