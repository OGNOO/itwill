package com.itwill.ver03;

import java.util.List;

import com.itwill.ver01.Contact;

public interface ContactDao {
	int create(Contact contact);

	List<Contact> read();

	Contact read(int index);

	int update(int index, Contact contact);
	
	int delete(int index);
}
