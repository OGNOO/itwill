package com.itwill.class06;

public class ClassMain06 {

	public static void main(String[] args) {
		int myAccount = 980421;
		int friendAccount = 981234;
		int newFriendAccount = 123456;
		int deposit = 5000;
		int withdrawal = 1000;

		Account account01 = new Account(myAccount, 100);
		Account account02 = new Account(friendAccount, 500);
		Account account03 = new Account(newFriendAccount, 0);

		System.out.println("입금 후 잔액: " + account01.deposit(deposit));
		System.out.println("출금 후 잔액: " + account01.withdraw(withdrawal));
		if (account01.transfer(account02, 1500) == true) {
			System.out.println("이체 성공!");
		} else {
			System.out.println("이체 실패!");
		}

		System.out.println("\n이체 후 내 계좌 잔액");
		account01.info();
		System.out.println("\n이체 후 친구 계좌 잔액");
		account02.info();
		System.out.println("\n");
		account03.info();
	}

}
