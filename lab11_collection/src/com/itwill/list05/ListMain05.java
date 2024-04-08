package com.itwill.list05;

import java.util.ArrayList;

public class ListMain05 {

	public static void main(String[] args) {
		// Member 타입 객체들을 저장하는 ArrayList 를 선언, 초기화
		ArrayList<Member> members = new ArrayList<>();

		// members 에 원소들을 저장
		members.add(new Member("admin", "1111"));
		members.add(new Member("guest", "guest"));
		members.add(new Member("itwill", "0410"));
		members.add(new Member("TEST", "test"));
		members.add(new Member("guest", "0000"));

//		for (int i = 0; i < members.size(); i++) {
//			System.out.println(members.get(i).toString());
//		}

		// 향상된 for 문장을 사용해서 members 의 원소들을 출력
		for (Member member : members) {
			System.out.println(member.toString());
		}
		System.out.println("======================================");

		// 회원 아이디에 "est" 가 포함된 회원 정보를 다른 리스트에 저장하고, 출력
		// 영문자의 대/소문자는 구분하지 않음
		ArrayList<Member> estMembers = new ArrayList<>();

		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getId().toLowerCase().contains("est")) {
				estMembers.add(members.get(i));
			}
		}
		for (Member estMember : estMembers) {
			System.out.println(estMember.toString());
		}

		// members 에서 첫번째로 등장하는 id 가 "guest" 인 회원 삭제
//		for (int i = 0; i < members.size(); i++) {
//			if (members.get(i).getId().toLowerCase().contains("guest")) {
//				members.remove(i);
//				break;
//			}
//		}

		members.remove(new Member("guest", null));

		System.out.println(members);
	}
}
