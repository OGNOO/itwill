package com.itwill.jdbc03;

import static com.itwill.jdbc.OracleJdbc.PASSWORD;
import static com.itwill.jdbc.OracleJdbc.URL;
import static com.itwill.jdbc.OracleJdbc.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

public class JdbcMain03 {

	public static void main(String[] args) throws SQLException {
		// update 문장 실행 & 결과 처리
		PreparedStatement stmt = null;

		// 오라클 드라이버를 등록
		DriverManager.registerDriver(new OracleDriver());

		// 오라클 DB 접속
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

		final String sql = "update blogs set title = ?, content = ?, modified_time = systimestamp where id = ?";
		stmt = conn.prepareStatement(sql);

		// PreparedStatement 객체의 SQL 에서 ? 부분을 입력받은 내용으로 채움.
		stmt.setString(1, "update 테스트"); // 첫번째 ? 에 title 변수의 값을 문자열로 채움.
		stmt.setString(2, "업데이트 테스트"); // 두번째 ? 에 content 변수의 값을 문자열로 채움.
		stmt.setInt(3, 2); // 세번째 ? 에 writer 변수의 값을 문자열로 채움.

		int result = stmt.executeUpdate();
		System.out.println(result + " 개 행이 업데이트됨");

		stmt.close(); // PreparedStatement 해제.
		conn.close(); // 오라클 접속 끊기.
	}
}
