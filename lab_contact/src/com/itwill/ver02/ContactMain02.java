package com.itwill.ver02;

import java.util.Scanner;

import com.itwill.ver01.Contact;

public class ContactMain02 {

	private final Scanner scanner = new Scanner(System.in);
	private ContactDao dao = ContactDaoImpl.getInstance();

	public static void main(String[] args) {
		System.out.println("\t ***** 연락처 프로그램 v0.2 *****");

		ContactMain02 app = new ContactMain02();

		boolean run = true;
		while (run) {
			int menu = app.selectMainMenu();
			switch (menu) {
			case 0:
				run = false;
				break;
			case 1:
				app.saveNewMember();
				break;
			case 2:
				app.readAllMembers();
				break;
			case 3:
				app.readMemberByIndex();
				break;
			case 4:
				app.updateMember();
				break;
			default:
				System.out.println("0 ~ 4 범위의 정수로 입력하세요...");
			}
		}
		System.out.println(">>> 프로그램 종료 <<<");
	}

	private void updateMember() {
		System.out.println("\n=====================[ 연락처 수정 ]=====================");

		int index = 0;
		Contact contact = dao.read(index);

		if (contact != null) {

			index = inputInteger("인덱스 수정>> ");

			if (!((ContactDaoImpl) dao).isValidIndex(index)) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			contact = dao.read(index);

			System.out.println("수정 할 인덱스: " + index);
			System.out.println("\n수정할 정보가 없으면 Enter를 눌러주세요");
			System.out.print("새 이름>> ");
			String newName = scanner.nextLine();
			if (!((ContactDaoImpl) dao).isNotNull(newName)) {
				contact.setName(contact.getName());
			} else {
				contact.setName(newName);
			}

			System.out.print("새 전화번호>> ");
			String newPhone = scanner.nextLine();

			if (!((ContactDaoImpl) dao).isNotNull(newPhone)) {
				contact.setPhone(contact.getPhone());
			} else {
				contact.setPhone(newPhone);
			}

			System.out.print("새 이메일>> ");
			String newEmail = scanner.nextLine();

			if (!((ContactDaoImpl) dao).isNotNull(newEmail)) {
				contact.setEmail(contact.getEmail());
			} else {
				contact.setEmail(newEmail);
			}

			int result = dao.update(index, contact);

			if (result == 1) {
				System.out.println("연락처 업데이트 성공");
			} else {
				System.out.println("연락처 업데이트 실패");
			}
		} else {
			System.out.println("저장된 연락처가 없습니다.");
		}
	}

	private void readMemberByIndex() {
		System.out.println("\n=====================[ 인덱스 검색 ]=====================");

		int index = 0;
		Contact contact = dao.read(index);

		if (contact != null) {
			index = inputInteger("검색할 인덱스 입력>> ");

			if (!((ContactDaoImpl) dao).isValidIndex(index)) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			contact = dao.read(index);

			// View 에서 Controller 의 기능을 사용해서 해당 인덱스의 회원 정보를 가져옴
			if (contact != null) {
				System.out.println(contact);
			} else {
				System.out.println("해당 인덱스에는 회원 정보 없습니다.");
			}
		} else {
			System.out.println("검색할 인덱스가 없습니다.");
		}
	}

	private void readAllMembers() {
		System.out.println("\n=====================[ 연락처 목록 ]=====================");

		Contact[] contacts = dao.read(); // View 에서 Controller 기능을 사용, 출력할 데이터를 가져옴.

		if (contacts.length != 0) {
			for (int i = 0; i < contacts.length; i++) {
				if (contacts[i] != null) {
					System.out.println(contacts[i]);
				}
			}
		} else {
			System.out.println("저장된 회원 정보가 없습니다");
		}
	}

	private void saveNewMember() {
		System.out.println("\n=====================[ 연락처 저장 ]=====================");

		ContactDaoImpl daoImpl = (ContactDaoImpl) dao;

		if (daoImpl.isMemoryFull()) {
			System.out.println("회원 정보를 저장할 메모리가 부족합니다.");
			return;
		}

		System.out.print("이름 입력>> ");
		String name = scanner.nextLine();

		if (!((ContactDaoImpl) dao).isNotNull(name)) {
			System.out.println("잘못된 이름 입니다.");
			return;
		}

		System.out.print("전화번호 입력>> ");
		String phone = scanner.nextLine();

		if (!((ContactDaoImpl) dao).isNotNull(phone)) {
			System.out.println("잘못된 전화번호 입니다.");
			return;
		}

		System.out.println("\n입력할 이메일이 없으면 Enter를 눌러주세요");
		System.out.print("이메일 입력>> ");
		String email = scanner.nextLine();

		Contact contact = new Contact(name, phone, email);
		int result = dao.create(contact); // View 객체에서 Controller 객체의 기능을 사용.

		if (result == 1) {
			System.out.println("회원 정보 저장 성공");
		} else {
			System.out.println("회원 정보 저장 실패");
		}
	}

	private int selectMainMenu() {
		System.out.println("\n========================================================");
		System.out.println(" [0]종료 [1]저장 [2]목록 [3]인덱스검색 [4]수정 ");
		System.out.println("========================================================");

		int menu = inputInteger("선택> ");

		return menu;
	}

	private int inputInteger(String str) {
		boolean run = true;
		int result = 0;

		while (run) {
			try {
				System.out.print(str);
				result = Integer.parseInt(scanner.nextLine());
				run = false;
			} catch (NumberFormatException e) {
				System.out.println(" !! 정수로 입력해주세요. !!");
			}
		}

		return result;
	}
}
