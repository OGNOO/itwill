package com.itwill.exception06;

public class ExceptionMain06 {

	public static void main(String[] args) throws Exception {
		// throws 선언문이 있는 메서드들 중에서,
		// RuntimeException 을 상속하는 예외 타입들은 try-catch 를 사용하지 않아도 컴파일 에러가 없음
		// RuntimeException 이 아닌 예외 타입들은 반드시
		//  (1) try-catch 문장을 사용하거나 (예외를 받아서 처리해주거나)
		//  (2) throws 선언문을 추가해야 함 (예외를 다른데에 던지거나)
		
		// Integer.parseInt("") // -> 반드시 try-catch 를 사용할 필요는 없음
		
		// Calculator 타입 객체 생성
		Calculator calc = new Calculator();

//		try {
		int result = calc.divide(100, 0);
		System.out.println(result);

//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.err.println("=====================");
//			System.err.println("[ERROR] 메시지 내용");
//			System.err.println("=====================");		}
		System.out.println("main 정상 종료");
	}
}
