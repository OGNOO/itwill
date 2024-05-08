package com.itwill.order;

public interface OracleJdbc {
	// 오라클 데이터베이스에 접속하기 위한 라이브러리 정보와 서버 주소/포트/SID 정보
//	String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String URL = "jdbc:oracle:thin:@192.168.20.27:1521:xe";
	String USER = "scott";
	String PASSWORD = "tiger";
	
	
	// 집 전용 ----->
//	String URL = "jdbc:oracle:thin:@192.168.219.102:1521:ORCL";
//	String USER = "system";
//	String PASSWORD = "1234";
	// <----- 집 전용
}
