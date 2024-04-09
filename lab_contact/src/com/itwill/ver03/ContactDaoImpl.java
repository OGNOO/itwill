package com.itwill.ver03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.itwill.ver01.Contact;

public class ContactDaoImpl implements ContactDao {
	// field
	private List<Contact> contacts = new ArrayList<>();

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
	public boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
		return pattern.matcher(email).matches();
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		boolean result = false;
		if (phoneNumber.length() == 13) {
			Pattern pattern = Pattern.compile("[0-9]{3}-[0-9]{4}-[0-9]{4}");
			result = pattern.matcher(phoneNumber).matches();
		} else if (phoneNumber.length() == 11) {
			Pattern pattern = Pattern.compile("[0-9]{11}");
			result = pattern.matcher(phoneNumber).matches();
		}
		return result;
	}

	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < contacts.size());
	}

	@Override
	public int create(Contact contact) {
		if (contact.getPhone().length() != 13) {
			contact.setPhone(contact.getPhone().substring(0, 3) + "-" + contact.getPhone().substring(3, 7) + "-"
					+ contact.getPhone().substring(7));
		}
		contacts.add(contact);
		return 1;
	}

	@Override
	public ArrayList<Contact> read() {
		return (ArrayList<Contact>) contacts;
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
			if (contacts.get(index).getPhone().length() != 13) {
				contacts.get(index)
						.setPhone(contacts.get(index).getPhone().substring(0, 3) + "-"
								+ contacts.get(index).getPhone().substring(3, 7) + "-"
								+ contacts.get(index).getPhone().substring(7));
			}
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
