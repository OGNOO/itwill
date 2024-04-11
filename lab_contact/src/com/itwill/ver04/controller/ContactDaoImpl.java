package com.itwill.ver04.controller;

import java.util.List;
import java.util.regex.Pattern;

import com.itwill.ver04.model.Contact;

// import static 문장: 클래스에서 public static 으로 선언된 멤버(필드, 메서드)의 이름 가져옴
//import static com.itwill.ver04.util.FileUtil.initializeData;
import static com.itwill.ver04.util.FileUtil.*;

// MVC 아키텍쳐에서 Controller 역할 담당 클래스. DAO(Data Access Object)
public class ContactDaoImpl implements ContactDao {
	// singleton 구현-----
	private static ContactDaoImpl instance = null;

	public static ContactDaoImpl getInstance() {
		if (instance == null) {
			instance = new ContactDaoImpl();
		}
		return instance;
	}
	// -----singleton 구현

	// field
	private List<Contact> contacts;

	// constructor
	private ContactDaoImpl() {
		// 데이터 폴더를 초기화: 폴더가 없으면 새로 만듦
		initializeDataDir();
		// 연락처 데이터를 초기화: 데이터 파일에서 리스트를 읽어오거나, 빈 리스트를 생성
		contacts = initializeData();
	}

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

	/**
	 * 인덱스가 연락처 리스트의 유효한 범위 안에 있는 지를 리턴.
	 * 
	 * @param index 검사할 인덱스.
	 * @return 유효한 인덱스 true, 그렇지 않으면 false.
	 */
	public boolean isValidIndex(int index) {
		return (index >= 0) && (index < contacts.size());
	}

	@Override
	public int create(Contact contact) {
		if (contact.getPhone().length() != 13) {
			contact.setPhone(contact.getPhone().substring(0, 3) + "-" + contact.getPhone().substring(3, 7) + "-"
					+ contact.getPhone().substring(7));
		}
		contacts.add(contact); // 리스트(메모리)에 추가
		writeDataToFile(contacts); // 파일에 씀
		return 1;
	}

	@Override
	public List<Contact> read() {
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
			if (contacts.get(index).getPhone().length() != 13) {
				contacts.get(index)
						.setPhone(contacts.get(index).getPhone().substring(0, 3) + "-"
								+ contacts.get(index).getPhone().substring(3, 7) + "-"
								+ contacts.get(index).getPhone().substring(7));
			}
			contacts.get(index).setPhone(contact.getPhone());
			contacts.get(index).setEmail(contact.getEmail());
			writeDataToFile(contacts);
			return 1;
		}
		return 0;
	}

	@Override
	public int delete(int index) {
		if (isValidIndex(index)) {
			contacts.remove(index);
			writeDataToFile(contacts);
			return 1;
		}
		return 0;
	}

}
