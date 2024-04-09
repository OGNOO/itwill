package com.itwill.set03;

import java.util.Objects;

public class Score {
	// field
	private int java;
	private int sql;
	private int html;

	// constructor
	public Score() {
	}

	public Score(int java, int sql, int html) {
		this.java = java;
		this.sql = sql;
		this.html = html;
	}

	// method
	@Override
	public int hashCode() {
//		return html + java + sql;
		return Objects.hash(java, html, sql);
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (this != null && obj instanceof Score) {
			Score other = (Score) obj;
			result = (java == other.java) && (sql == other.sql) && (html == other.html);
		}
		return result;
	}

	@Override
	public String toString() {
		return "Score [java=" + java + ", sql=" + sql + ", html=" + html + "]";
	}

	// getterSetter
	public int getJava() {
		return java;
	}

	public void setJava(int java) {
		this.java = java;
	}

	public int getSql() {
		return sql;
	}

	public void setSql(int sql) {
		this.sql = sql;
	}

	public int getHtml() {
		return html;
	}

	public void setHtml(int html) {
		this.html = html;
	}

}
