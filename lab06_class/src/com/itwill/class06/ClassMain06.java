package com.itwill.class06;

public class ClassMain06 {

	public static void main(String[] args) {
		int myAccount = 980421;
		int friendAccount = 981234;
		int newFriendAccount = 123456;
		int deposit = 5000;
		int withdrawal = 1000;

		Account account01 = new Account(myAccount, 0);
		Account account02 = new Account(friendAccount, 0);
		Account account03 = new Account(newFriendAccount, 0);

		System.out.println("입금 후 잔액: " + account01.deposit(deposit));
		System.out.println("출금 후 잔액: " + account01.withdraw(withdrawal));

		account01.transfer(account02, 500);
		System.out.println("\n이체 후 내 계좌 잔액");
		account01.info();
		System.out.println("\n이체 후 친구 계좌 잔액");
		account02.info();
		System.out.println("\n");
		account03.info();
	}

}
