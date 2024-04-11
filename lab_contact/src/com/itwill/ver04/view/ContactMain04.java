package com.itwill.ver04.view;

import java.util.Scanner;

import com.itwill.ver04.controller.ContactDao;
import com.itwill.ver04.controller.ContactDaoImpl;
import com.itwill.ver04.model.Contact;

// View
public class ContactMain04 {

	private final Scanner scanner = new Scanner(System.in);
	private ContactDao dao = ContactDaoImpl.getInstance();

	public static void main(String[] args) {
		System.out.println("\n\t   ***** 연락처 프로그램 v0.4 *****");

		ContactMain04 app = new ContactMain04();

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
				app.readMemberByIndex(true);
				break;
			case 4:
				app.updateMember();
				break;
			case 5:
				app.deleteMemberByIndex();
				break;
			default:
				System.out.println("0 ~ 4 범위의 정수로 입력하세요...");
			}
		}
		System.out.println(">>> 프로그램 종료 <<<");
	}

	private void deleteMemberByIndex() {
		System.out.println("\n====================[ 연락처 삭제 ]====================");
		int index = readMemberByIndex(false);

		if (index == -1)
			return;

		System.out.print(" 정말 삭제하시겠습니까? ( y / n ) >> ");
		String yOrN = scanner.nextLine();

		boolean realDelete = yOrN.equals("y") ? true : false;

		if (!realDelete)
			return;

		int result = dao.delete(index);

		if (result == 1) {
			System.out.println("연락처가 삭제 되었습니다.");
		} else {
			System.out.println("연락처 삭제 실패");
		}
	}

	private void updateMember() {
		System.out.println("\n====================[ 연락처 수정 ]====================");

		int index = readMemberByIndex(false);

		if (index == -1)
			return;

		Contact contact = dao.read(index);

		System.out.println("\n수정할 정보가 없으면 Enter를 눌러주세요");
		System.out.print("새 이름>> ");
		String newName = scanner.nextLine();
		if (newName.isBlank()) {
			contact.setName(contact.getName());
		} else {
			contact.setName(newName);
		}

		System.out.print("새 전화번호>> ");
		String newPhone = scanner.nextLine();

		if (!((ContactDaoImpl) dao).isValidPhoneNumber(newPhone) && !newPhone.isBlank()) {
			// 유효하지 않은 전화번호
			System.out.println("유효하지 않은 전화번호 입니다.");
			System.out.println("Ex1.) 010-1234-1234");
			System.out.println("Ex2.) 01012341234");
			return;
		}

		if (newPhone.isBlank()) {
			contact.setPhone(contact.getPhone());
		} else {
			contact.setPhone(newPhone);
		}

		System.out.print("새 이메일>> ");
		String newEmail = scanner.nextLine();

		if (!((ContactDaoImpl) dao).isValidEmail(newEmail) && !newEmail.isBlank()) {
			// 유효하지 않은 이메일
			System.out.println("유효하지 않은 이메일 입니다.");
			return;
		}

		if (newEmail.isBlank()) {
			contact.setEmail(contact.getEmail());
		} else {
			contact.setEmail(newEmail);
		}

		int result = dao.update(index, contact);

		if (result == 1 && !newName.isBlank()) {
			System.out.println(newName + "님으로 연락처가 변경 되었습니다.");
		} else if (newName.isBlank() && newPhone.isBlank() && newEmail.isBlank()) {
			System.out.println(contact.getName() + "님의 연락처 변경이 취소 되었습니다.");
		} else if (newName.isBlank()) {
			System.out.println(contact.getName() + "님의 연락처가 변경 되었습니다.");
		} else {
			System.out.println("연락처 수정 실패");
		}
	}

	private int readMemberByIndex(boolean printMenuHead) {
		if (printMenuHead) {
			System.out.println("\n====================[ 연락처 검색 ]====================");
		}
		int index = 0;
		Contact contact = dao.read(index);

		if (contact != null) {
			index = inputInteger("검색>> ");

			if (!((ContactDaoImpl) dao).isValidIndex(index)) {
				System.out.println("일치하는 연락처가 없습니다.");
				return -1;
			}
			contact = dao.read(index);

			System.out.println(contact);

			return index;
		} else {
			System.out.println("저장된 연락처가 없습니다.");
			return -1;
		}

	}

	private void readAllMembers() {
		System.out.println("\n====================[ 연락처 목록 ]====================");

		if (!dao.read().isEmpty()) {
			for (int i = 0; i < dao.read().size(); i++) {
				System.out.println(dao.read().get(i));
			}
		} else {
			System.out.println("저장된 연락처가 없습니다");
		}
	}

	private void saveNewMember() {
		System.out.println("\n====================[ 연락처 저장 ]====================");

		System.out.print("이름 입력>> ");
		String name = scanner.nextLine();

		if (name.isBlank()) {
			System.out.println("잘못된 이름 입니다.");
			return;
		}

		System.out.print("전화번호 입력>> ");
		String phone = scanner.nextLine();

		if (phone.isBlank()) {
			System.out.println("전화번호를 입력해주세요.");
			return;
		}

		if (!((ContactDaoImpl) dao).isValidPhoneNumber(phone)) {
			// 유효하지 않은 전화번호
			System.out.println("유효하지 않은 전화번호 입니다.");
			System.out.println("Ex1.) 010-1234-1234");
			System.out.println("Ex2.) 01012341234");
			return;
		}

		System.out.println("\n입력할 이메일이 없으면 Enter를 눌러주세요");
		System.out.print("이메일 입력>> ");
		String email = scanner.nextLine();

		if (email.isBlank()) {
			email = " ";
		}

		if (!((ContactDaoImpl) dao).isValidEmail(email) && !email.isBlank()) {
			// 유효하지 않은 이메일
			System.out.println("유효하지 않은 이메일 입니다.");
			return;
		}

		Contact contact = new Contact(0, name, phone, email);
		int result = dao.create(contact);

		if (result == 1) {
			System.out.println(name + "님의 연락처가 저장 되었습니다.");
		} else {
			System.out.println("연락처 저장 실패");
		}

	}

	private int selectMainMenu() {
		System.out.println("\n=======================================================");
		System.out.println(" [0]종료 [1]저장 [2]목록 [3]인덱스검색 [4]수정 [5]삭제");
		System.out.println("=======================================================");

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
