package com.itwill.order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.order.model.Product;
import com.itwill.order.model.ProductLog;

import oracle.jdbc.driver.OracleDriver;

import static com.itwill.order.OracleJdbc.*;
import static com.itwill.order.model.Product.Entity.*;
import static com.itwill.order.model.ProductLog.Entity.*;

public class ProductLogDao {
	// -----> singleton
	private static ProductLogDao instance = null;

	private ProductLogDao() {
		try {
			// Oracle 드라이버(라이브러리)를 등록.
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ProductLogDao getInstance() {
		if (instance == null) {
			instance = new ProductLogDao();
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

	private void closeResources(Connection conn, Statement stmt) {
		closeResources(conn, stmt, null);
	}

	private ProductLog makeProductLogFromResultSet(ResultSet rs) throws SQLException {
		int logId = rs.getInt(COL_LOG_ID);
		String cudData = rs.getString(COL_CUD_DATA);
		LocalDateTime time = rs.getTimestamp(COL_TIME).toLocalDateTime();
		String prvsCategory = rs.getString(COL_PRVS_CATEGORY);
		String aftrCategory = rs.getString(COL_AFTR_CATEGORY);
		String pName = rs.getString(COL_P_NAME);
		int prvsCurrentInven = rs.getInt(COL_PRVS_CURRENT_INVEN);
		int aftrCurrentInven = rs.getInt(COL_AFTR_CURRENT_INVEN);
		int prvsMinStk = rs.getInt(COL_PRVS_MIN_STK);
		int aftrMinStk = rs.getInt(COL_AFTR_MIN_STK);
		int prvsImsqob = rs.getInt(COL_PRVS_IMSQOB);
		int aftrImsqob = rs.getInt(COL_AFTR_IMSQOB);
		int prvsQnttyBndl = rs.getInt(COL_PRVS_QNTTY_BNDL);
		int aftrQnttyBndl = rs.getInt(COL_AFTR_QNTTY_BNDL);
		String cudManager = rs.getString(COL_CUD_MANAGER);

		ProductLog productLog = new ProductLog(logId, cudData, time, prvsCategory, aftrCategory, pName,
				prvsCurrentInven, aftrCurrentInven, prvsMinStk, aftrMinStk, prvsImsqob, aftrImsqob, prvsQnttyBndl,
				aftrQnttyBndl, cudManager);

		return productLog;
	}

//	private ProductLog makeFindConditionsToInsertIntoDeleteLog(ResultSet rs) throws SQLException {
//		
//		return productLog;
//	}

	private static final String SQL_SELECT_ALL = String.format("select * from %s order by %s desc", TBL_PRODUCT_LOG,
			COL_LOG_ID);

	public List<ProductLog> readShowLog() {
		List<ProductLog> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ProductLog productLog = makeProductLogFromResultSet(rs);
				result.add(productLog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return result;
	}

	private static final String SQL_INSERT_LOG = String.format(
			"insert into %s (%s, %s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?, ?)", TBL_PRODUCT_LOG,
			COL_CUD_DATA, COL_AFTR_CATEGORY, COL_P_NAME, COL_AFTR_CURRENT_INVEN, COL_AFTR_MIN_STK, COL_AFTR_IMSQOB,
			COL_AFTR_QNTTY_BNDL, COL_CUD_MANAGER);

	public void insertNewProductLog(ProductLog productLog) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_INSERT_LOG);
			stmt.setString(1, productLog.getCudData());
			stmt.setString(2, productLog.getAftrCategory());
			stmt.setString(3, productLog.getpName());
			stmt.setInt(4, productLog.getAftrCurrentInven());
			stmt.setInt(5, productLog.getAftrMinStk());
			stmt.setInt(6, productLog.getAftrImsqob());
			stmt.setInt(7, productLog.getAftrQnttyBndl());
			stmt.setString(8, productLog.getCudManager());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}

	}

	private static final String SQL_SELECT_PRVS_PRODUCT_NAME = String.format("select * from %s where %s = ?",
			TBL_PRODUCT, COL_PRODUCT_NAME);

	private static final String SQL_INSERT_DELETE_LOG = String.format(
			"insert into %s (%s, %s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?, ?)", TBL_PRODUCT_LOG,
			COL_CUD_DATA, COL_PRVS_CATEGORY, COL_P_NAME, COL_PRVS_CURRENT_INVEN, COL_PRVS_MIN_STK, COL_PRVS_IMSQOB,
			COL_PRVS_QNTTY_BNDL, COL_CUD_MANAGER);

	public void deleteLog(String[] selectedProductNames, String selectedOption) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			for (String s : selectedProductNames) {
				stmt = conn.prepareStatement(SQL_SELECT_PRVS_PRODUCT_NAME);
				stmt.setString(1, s);
				rs = stmt.executeQuery();
				while (rs.next()) {
					String productCategory = rs.getString(COL_PRODUCT_CATEGORY);
					String productName = rs.getString(COL_PRODUCT_NAME);
					int currentInven = rs.getInt(COL_CURRENT_INVEN);
					int minStk = rs.getInt(COL_MIN_STK);
					int imsqob = rs.getInt(COL_IMSQOB);
					int qnttyBndl = rs.getInt(COL_QNTTY_BNDL);
					stmt = conn.prepareStatement(SQL_INSERT_DELETE_LOG);

					stmt.setString(1, "삭제");
					stmt.setString(2, productCategory);
					stmt.setString(3, productName);
					stmt.setInt(4, currentInven);
					stmt.setInt(5, minStk);
					stmt.setInt(6, imsqob);
					stmt.setInt(7, qnttyBndl);
					stmt.setString(8, selectedOption);

					stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

	}

	private static final String SQL_INSERT_UPDATE_LOG = String.format(
			"insert into %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			TBL_PRODUCT_LOG, COL_CUD_DATA, COL_PRVS_CATEGORY, COL_AFTR_CATEGORY, COL_P_NAME, COL_PRVS_CURRENT_INVEN,
			COL_AFTR_CURRENT_INVEN, COL_PRVS_MIN_STK, COL_AFTR_MIN_STK, COL_PRVS_IMSQOB, COL_AFTR_IMSQOB,
			COL_PRVS_QNTTY_BNDL, COL_AFTR_QNTTY_BNDL, COL_CUD_MANAGER);

	public void updateLog(Product product, String selectedOption) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_PRVS_PRODUCT_NAME);
			stmt.setString(1, product.getProductName());
			rs = stmt.executeQuery();
			while (rs.next()) {
				String productCategory = rs.getString(COL_PRODUCT_CATEGORY);
				String productName = rs.getString(COL_PRODUCT_NAME);
				int currentInven = rs.getInt(COL_CURRENT_INVEN);
				int minStk = rs.getInt(COL_MIN_STK);
				int imsqob = rs.getInt(COL_IMSQOB);
				int qnttyBndl = rs.getInt(COL_QNTTY_BNDL);
				stmt = conn.prepareStatement(SQL_INSERT_UPDATE_LOG);

				stmt.setString(1, "수정");
				stmt.setString(2, productCategory);
				stmt.setString(3, product.getProductCategory());
				stmt.setString(4, productName);
				stmt.setInt(5, currentInven);
				stmt.setInt(6, product.getCurrentInven());
				stmt.setInt(7, minStk);
				stmt.setInt(8, product.getMinStk());
				stmt.setInt(9, imsqob);
				stmt.setInt(10, product.getImsqob());
				stmt.setInt(11, qnttyBndl);
				stmt.setInt(12, product.getQnttyBndl());
				stmt.setString(13, selectedOption);
				
				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
	}
}
