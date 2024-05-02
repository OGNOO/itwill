package com.itwill.order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

import static com.itwill.order.OracleJdbc.*;
import static com.itwill.order.model.Manager.Entity.*;

public class ManagerDao {
	// -----> singleton
	private static ManagerDao instance = null;

	private ManagerDao() {
		try {
			// Oracle 드라이버(라이브러리)를 등록.
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ManagerDao getInstance() {
		if (instance == null) {
			instance = new ManagerDao();
		}
		return instance;
	}

	// <----- singleton
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static final String SQL_SELECT_BY_NAME_AND_PW = String.format("select * from %s where %s = ? and %s = ?",
			TBL_MANAGER_ACCOUNT, COL_NAME, COL_PASSWORD);

	public int login(String name, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_NAME_AND_PW);
			stmt.setString(1, name);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
	}

	private static final String SQL_SELECT_BY_NAME = String.format("select %s from %s", COL_NAME, TBL_MANAGER_ACCOUNT);

	public String[] readAdminName() {
		String[] selectAdmin = new String[5];
		int i = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_NAME);
			rs = stmt.executeQuery();
			while (rs.next()) {
				selectAdmin[i] = rs.getString(COL_NAME);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return selectAdmin;
	}

	private static final String SQL_SELECT_CRUD = String.format("select %s from %s where %s = ?", COL_CRUD,
			TBL_MANAGER_ACCOUNT, COL_NAME);

	public String checkCrud(String name) {
		String result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_CRUD);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(COL_CRUD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
