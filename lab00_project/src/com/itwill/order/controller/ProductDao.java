package com.itwill.order.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itwill.order.model.Product;

import oracle.jdbc.driver.OracleDriver;

import static com.itwill.order.OracleJdbc.*;
import static com.itwill.order.model.Product.Entity.*;

public class ProductDao {
	// -----> singleton
	private static ProductDao instance = null;

	private ProductDao() {
		try {
			// Oracle 드라이버(라이브러리)를 등록.
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
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

	public Product makeBasedProductFromResultSet(ResultSet rs) throws SQLException {
		String productCategory = rs.getString(COL_PRODUCT_CATEGORY);
		String productName = rs.getString(COL_PRODUCT_NAME);
		int currentInven = rs.getInt(COL_CURRENT_INVEN);
		int minStk = rs.getInt(COL_MIN_STK);
		int imsqob = rs.getInt(COL_IMSQOB);
		int qnttyBndl = rs.getInt(COL_QNTTY_BNDL);

		Product product = new Product(productCategory, productName, currentInven, minStk, imsqob, qnttyBndl);

		return product;
	}

	private static final String SQL_SELECT_ALL = String.format("select * from %s order by %s, %s", TBL_PRODUCT,
			COL_PRODUCT_CATEGORY, COL_PRODUCT_NAME);

	public List<Product> readBasedOrder() {
		List<Product> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = makeBasedProductFromResultSet(rs);
				result.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}

		return result;
	}

	private static final String SQL_INSERT = String.format(
			"insert into %s (%s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?)", TBL_PRODUCT, COL_PRODUCT_CATEGORY,
			COL_PRODUCT_NAME, COL_CURRENT_INVEN, COL_MIN_STK, COL_IMSQOB, COL_QNTTY_BNDL);

	public void insertNewProductInfo(Product product) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, product.getProductCategory());
			stmt.setString(2, product.getProductName());
			stmt.setInt(3, product.getCurrentInven());
			stmt.setInt(4, product.getMinStk());
			stmt.setInt(5, product.getImsqob());
			stmt.setInt(6, product.getQnttyBndl());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
	}

	private static final String SQL_DELETE = String.format("delete from %s where %s = ?", TBL_PRODUCT,
			COL_PRODUCT_NAME);

	public void delete(String[] selectedProductNames) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			for (int i = 0; i < selectedProductNames.length; i++) {
				stmt = conn.prepareStatement(SQL_DELETE);
				stmt.setString(1, selectedProductNames[i]);

				stmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
	}

	/*
	 * private static final String SQL_UPDATE = String.format(
	 * "update %s set %s = ?, %s = ?, %s = systimestamp where %s = ?", TBL_BLOGS,
	 * COL_TITLE, COL_CONTENT, COL_MODIFIED_TIME, COL_ID);
	 */
//	UPDATE customers
//	SET name = 'John Doe', email = 'johndoe@example.com'
//	WHERE customer_id = 123;
	private static final String SQL_UPDATE = String.format(
			"update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?", TBL_PRODUCT,
			COL_PRODUCT_CATEGORY, COL_PRODUCT_NAME, COL_CURRENT_INVEN, COL_MIN_STK, COL_IMSQOB, COL_QNTTY_BNDL,
			COL_PRODUCT_NAME);

	// 변경하기 이전의 상품명 넘겨줘야함
	public void update(Product product) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setString(1, product.getProductCategory());
			stmt.setString(2, product.getProductName());
			stmt.setInt(3, product.getCurrentInven());
			stmt.setInt(4, product.getMinStk());
			stmt.setInt(5, product.getImsqob());
			stmt.setInt(6, product.getQnttyBndl());
			stmt.setString(7, product.getProductName());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
	}
}
