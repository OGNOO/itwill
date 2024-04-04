package com.itwill.ver01;

import java.util.Scanner;

public class ContactMain {
	private static final int MAX_LENGTH = 3; // 연락처 배열의 최대 길이(원소 개수)

	private Scanner scanner = new Scanner(System.in);
	private Contact[] contacts = new Contact[MAX_LENGTH]; // 연락처를 저장하기 위한 배열
	private int count = 0; // 현재까지 연락처 배열에 저장된 개수
	// 배열에 새로운 연락처 저장될 때마다 count++ 을 실행

	public static void main(String[] args) {
		System.out.println("\t ***** 연락처 프로그램 v0.1 *****");

		ContactMain app = new ContactMain();
		// 프로그램 계속 실행(run = true) 또는 종료 (run = false) 여부를 저장하기 위한 변수
		boolean run = true;
		while (run) {
			// TODO 메인 메뉴 보여주기
			// 스캐너 입력
			int menu = app.showMainMenu();

			switch (menu) {
			case 0:
				// 프로그램 종료
				run = false;
				break;
			case 1:
				// 저장
				app.saveNewContact();
				break;
			case 2:
				// 목록
				app.readAllContacts();
				break;
			case 3:
				// 인덱스검색
				app.readContactByIndex(true);
				break;
			case 4:
				// 수정하기
				app.updateContactByIndex();
				break;
			case 5:
				// 삭제하기
				app.deleteContactByIndex();
				break;

			default:
				System.out.println(" 메뉴 번호(0 ~ 4)를 확인하세요.");
			}
		}
		System.out.println("\t ***** 연락처 프로그램 종료 *****");
	}

	private void deleteContactByIndex() {
		System.out.println("\n=====================[ 연락처 삭제 ]=====================");

		int index = readContactByIndex(false);

		if (index < count && index >= 0) {
			System.out.print(" 정말 삭제하시겠습니까? ( y / n ) >> ");
			String yOrN = scanner.nextLine();

			boolean realDelete = yOrN.equals("y") ? true : false;

			if (realDelete) {
				contacts[index].setName(null);
				contacts[index].setPhone(null);
				contacts[index].setEmail(null);
				count--;
				if (contacts[index + 1] != null) {
					for (int i = index; i <= count; i++) {
						contacts[index].setName(contacts[index + 1].getName());
						contacts[index].setPhone(contacts[index + 1].getPhone());
						contacts[index].setEmail(contacts[index + 1].getEmail());
					}
				}
			}
		}
	}

	private void updateContactByIndex() {
		System.out.println("\n=====================[ 연락처 수정 ]=====================");

		int index = readContactByIndex(false);

		if (index < count && index >= 0) {
			System.out.print(" 이름 수정>> ");
			String name = scanner.nextLine();
			contacts[index].setName(name);

			System.out.print(" 전화번호 수정>> ");
			String phone = scanner.nextLine();
			contacts[index].setPhone(phone);

			System.out.print(" 이메일 수정>> ");
			String email = scanner.nextLine();
			contacts[index].setEmail(email);
		}
	}

	/**
	 * 파라미터로 전달 받은 boolean 값으로 인덱스 검색 텍스트를 출력할지 말지 결정하고, 인덱스 값이 올바른지? 연락처에 값이 있는지?
	 * 판별한 후 스캐너로 입력받은 인덱스 값을 리턴함
	 * 
	 * @param printTextIndexSearch true == 인덱스 검색 텍스트를 출력, false == 인덱스 검색 텍스트를 출력하지
	 *                             않음
	 * @return 스캐너로 입력받은 인덱스 값 리턴
	 */
	private int readContactByIndex(boolean printTextIndexSearch) {
		int index = -1; // index 초기값 false

		if (printTextIndexSearch == true) {
			System.out.println("\n=====================[ 인덱스 검색 ]=====================");
		}
		if (count != 0) {
			System.out.print(" 인덱스 입력>> ");
			index = Integer.parseInt(scanner.nextLine());
			if (index < count && index >= 0) {
				System.out.println(contacts[index].toString());
			} else {
				System.out.println("\n 잘못된 접근 입니다.");
			}
		} else {
			System.out.println("\n 저장된 연락처가 없습니다.");
		}
		return index;
	}

	private void readAllContacts() {
		System.out.println("\n=====================[ 연락처 목록 ]=====================");

		if (count > 0) {
			for (int i = 0; i < count; i++) {
				System.out.println(" 인덱스: " + i + ", " + contacts[i].toString());
			}
		} else {
			System.out.println("\n 저장된 연락처가 없습니다.");
		}
	}

	private void saveNewContact() {
		if (count < MAX_LENGTH) {
			if (count != MAX_LENGTH) {
				System.out.println("\n=====================[ 새 연락처 저장 ]=====================");

				System.out.print(" 이름 입력>> ");
				String name = scanner.nextLine();

				System.out.print(" 전화번호 입력>> ");
				String phone = scanner.nextLine();

				System.out.print(" 이메일 입력>> ");
				String email = scanner.nextLine();

				Contact contact = new Contact(name, phone, email);

				// Contact 타입 객체를 연락처 배열 인덱스 count 에 저장
				contacts[count] = contact;
				// 배열에 저장 후에는 연락처 저장 개수(인덱스)를 증가
				count++;

				System.out.println("\n 연락처 저장 성공!");
			}
		} else {
			System.out.println("\n 저장 공간이 부족합니다.");
		}
	}

	private int showMainMenu() {
		System.out.println("\n============================================================");
		System.out.println(" [0]종료 [1]저장 [2]목록 [3]인덱스검색 [4]수정하기 [5] 삭제");
		System.out.println("============================================================");
		System.out.print(" 선택> ");
		int menu = inputInteger();

		return menu;
	}

	private int inputInteger() {
		boolean run = true;
		int result = 0;

		while (run) {
			try {
				result = Integer.parseInt(scanner.nextLine());
				run = false;
			} catch (NumberFormatException e) {
				System.out.println(" !! 정수가 아닙니다. !!");
			}
		}

		return result;
	}
}
