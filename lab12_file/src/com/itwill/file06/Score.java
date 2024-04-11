package com.itwill.file06;

import java.io.Serializable;

public class Score implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// field
	private int java;
	private int web;
	private int spring;

	// constructor
	public Score() {
	}

	public Score(int java, int web, int spring) {
		super();
		this.java = java;
		this.web = web;
		this.spring = spring;
	}

	// method
	@Override
	public String toString() {
		return "Score [java=" + java + ", web=" + web + ", spring=" + spring + "]";
	}

	// getterSetter
	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getWeb() {
		return web;
	}

	public void setWeb(int web) {
		this.web = web;
	}

	public int getSpring() {
		return spring;
	}

	public void setSpring(int spring) {
		this.spring = spring;
	}

}
