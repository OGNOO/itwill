package com.itwill.ver03;

import java.util.ArrayList;

import com.itwill.ver01.Contact;

public class ContactDaoImpl implements ContactDao {
	// field
	private ArrayList<Contact> contacts = new ArrayList<>();

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
	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < contacts.size());
	}

	@Override
	public int create(Contact contact) {
		contacts.add(contact);
		return 1;
	}

	@Override
	public ArrayList<Contact> read() {
		return contacts;
	}

	@Override
	public Contact read(int index) {
		if (isValidIndex(index)) {
			return contacts.get(index);
		}
		return null;
	}

	@Override
	public int update(int index, Contact contact) {
		if (isValidIndex(index)) {
			contacts.get(index).setName(contact.getName());
			contacts.get(index).setPhone(contact.getPhone());
			contacts.get(index).setEmail(contact.getEmail());
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int index) {
		if (isValidIndex(index)) {
			contacts.remove(index);
			return 1;
		}
		return 0;
	}

}
