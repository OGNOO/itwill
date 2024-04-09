package com.itwill.ver02;

import com.itwill.ver01.Contact;

public interface ContactDao {
	int MAX_LENGTH = 3;

	int create(Contact contact);

	Contact[] read();

	Contact read(int index);

	int update(int index, Contact contact);
}
