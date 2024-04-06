package com.itwill.ver02;

import com.itwill.ver01.Contact;

public class ContactDaoImpl implements ContactDao {
	// field
	// 연락처 정보를 저장할 배열 선언
	private Contact[] contacts = new Contact[MAX_LENGTH];
	private int count = 0;

	// constructor
	private ContactDaoImpl() {
	}

	// singleton 구현-----
	private static ContactDaoImpl instance = null;

	public static ContactDaoImpl getInstance() {
		if (instance == null) {
			instance = new ContactDaoImpl();
		}
		return instance;
	}
	// -----singleton 구현

	// method
	public boolean isNotNull(String str) {
		boolean result = false;

		if (!str.equals("") && str.charAt(0) != ' ') {
			result = true;
		}
		return result;
	}

	public boolean isMemoryFull() {
		return (count == MAX_LENGTH);
	}

	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < count);
	}

	@Override
	public int create(Contact contact) {
		if (isMemoryFull()) {
			return 0;
		}
		contacts[count] = contact;
		count++;

		return 1;
	}

	@Override
	public Contact[] read() {
		Contact[] result = new Contact[count];
		for (int i = 0; i < count; i++) {
			result[i] = contacts[i];
		}

		return result;
	}

	@Override
	public Contact read(int index) {
		if (isValidIndex(index)) {
			return contacts[index];
		} else {
			return null;
		}
	}

	@Override
	public int update(int index, Contact contact) {
		if (isValidIndex(index)) {
			contacts[index].setName(contact.getName());
			contacts[index].setPhone(contact.getPhone());
			contacts[index].setEmail(contact.getEmail());
			return 1;
		} else {
			return 0;
		}
	}
}
