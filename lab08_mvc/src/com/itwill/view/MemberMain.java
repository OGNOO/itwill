package com.itwill.view;

import java.util.Scanner;

import com.itwill.controller.MemberDao;
import com.itwill.controller.MemberDaoImpl;
import com.itwill.model.Member;

// MVC 아키텍쳐에서 뷰(UI: User Interface) 역할을 담당하는 클래스
public class MemberMain {

	private final Scanner scanner = new Scanner(System.in);
	private MemberDao dao = MemberDaoImpl.getInstance(); // 컨트롤러(controller)

	public static void main(String[] args) {
		System.out.println("*** 회원 관리 프로그램 ***");

		// MemberMain 타입 객체의 인스턴스 멤버(필드, 메서드)를 사용하기 위해서
		MemberMain app = new MemberMain();

		boolean run = true; // 프로그램을 종료할 때 false 로 변경
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
		int index = 0;
		Member member = dao.read(index);
		System.out.println("\n=====================[ 회원 정보 업데이트 ]=====================");
		if (member != null) {
			System.out.print("인덱스 수정>> ");

			index = Integer.parseInt(scanner.nextLine());

			if (!((MemberDaoImpl) dao).isValidIndex(index)) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			member = dao.read(index);

			System.out.println("수정 할 인덱스: " + index);
			System.out.print("기존 비밀번호를 입력해주세요>> ");
			String oldPw = scanner.nextLine();

			if (!member.getPassword().equals(oldPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				return;
			}

			System.out.print("새 비밀번호>> ");
			String newPw = scanner.nextLine();

			// View 에서 Controller 의 기능을 사용해서 비밀번호를 업데이트
			int result = dao.update(index, newPw);

			if (result == 1) {
				System.out.println("비밀번호 업데이트 성공");
			} else {
				System.out.println("비밀번호 업데이트 실패");
			}
		} else {
			System.out.println("저장된 회원정보가 없습니다.");
		}
	}

	private void readMemberByIndex() {
		int index = 0;
		Member member = dao.read(index);
		System.out.println("\n=====================[ 인덱스 검색 ]=====================");

		if (member != null) {
			System.out.print("검색할 인덱스 입력>> ");

			index = Integer.parseInt(scanner.nextLine());

			if (!((MemberDaoImpl) dao).isValidIndex(index)) {
				System.out.println("잘못된 접근입니다.");
				return;
			}
			member = dao.read(index);

			// View 에서 Controller 의 기능을 사용해서 해당 인덱스의 회원 정보를 가져옴
			if (member != null) {
				System.out.println(member);
			} else {
				System.out.println("해당 인덱스에는 회원 정보 없습니다.");
			}
		} else {
			System.out.println("검색할 인덱스가 없습니다.");
		}
	}

	private void readAllMembers() {
		Member[] members = dao.read(); // View 에서 Controller 기능을 사용, 출력할 데이터를 가져옴.
		System.out.println("\n=====================[ 회원 정보 목록 ]=====================");

		if (members.length != 0) {
			for (int i = 0; i < members.length; i++) {
				if (members[i] != null) {
					System.out.println(members[i]);
				}
			}
		} else {
			System.out.println("저장된 회원 정보가 없습니다");
		}
	}

	private void saveNewMember() {
		System.out.println("\n----- 새 회원 정보 저장 ----");

		MemberDaoImpl daoImpl = (MemberDaoImpl) dao;

		if (daoImpl.isMemoryFull()) {
			System.out.println("회원 정보를 저장할 메모리가 부족합니다.");
			return;
		}

		System.out.print("아이디 입력>> ");
		String id = scanner.nextLine();

		if (!((MemberDaoImpl) dao).isNotNull(id)) {
			System.out.println("잘못된 ID 입니다.");
			return;
		}

		System.out.print("비밀번호 입력>> ");
		String password = scanner.nextLine();

		if (!((MemberDaoImpl) dao).isNotNull(password)) {
			System.out.println("잘못된 비밀번호 입니다.");
			return;
		}

		Member member = new Member(id, password);
		int result = dao.create(member); // View 객체에서 Controller 객체의 기능을 사용.

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
		System.out.print("선택> ");

		int menu = Integer.parseInt(scanner.nextLine());

		return menu;
	}
}
