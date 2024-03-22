package com.itwill.rsp;

import java.util.Random;
import java.util.Scanner;

public class Rsp {

	public static void main(String[] args) {
		// 가위/바위/보 (Rock-Scissors-Paper) 게임:
		// 가위 - 0, 바위 - 1, 보 - 2

		// TODO: Random 타입 변수를 선언, 초기화.
		Random rand = new Random();

		// TODO: Scanner 타입 변수를 선언, 초기화.
		Scanner scanner = new Scanner(System.in);

		System.out.println("가위바위보 게임");
		System.out.println("---------------");
		System.out.println("[0] 가위");
		System.out.println("[1] 바위");
		System.out.println("[2] 보");
		System.out.println("---------------");
		System.out.print("선택>>> ");

		// 0 이상 3 미만의 정수 난수 1개를 변수(com)에 저장
		int com = rand.nextInt(3); // rand.nextInt(0, 3)
		// 사용자가 콘솔창에서 입력한 정수를 변수(user)에 저장
		int user = scanner.nextInt();

		System.out.println("유저: " + user);
		System.out.println("컴퓨터: " + com);

		if (user == com) {
			System.out.println("Tie");
		} else if (user - com == 1 || user - com == -2) {
			System.out.println("User win");
		} else
			System.out.println("Computer win");

		// 0 1 가위 바위
		// 0 2 가위 보 1
		// 1 0 바위 가위 1
		// 1 2 바위 보
		// 2 0 보 가위
		// 2 1 보 바위 1

		// TODO: 사용자가 콘솔창에서 입력한 정수를 변수(user)에 저장.
		// TODO: 0 이상 3 미만의 정수 난수 1개를 변수(computer)에 저장.
		// TODO: 가위바위보 게임 결과(User win/Computer win/Tie)를 출력.
		scanner.close();
	}

}
