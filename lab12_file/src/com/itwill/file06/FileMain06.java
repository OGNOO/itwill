package com.itwill.file06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileMain06 {

	public static void main(String[] args) {
		// TODO Student 1_000_000 개를 저장하는 더미 데이터(ArrayList)를 만듦
		// 더미 데이터를 파일에 씀. 파일에 쓰는데 걸린 시간도 측정
		ArrayList<Student> list = new ArrayList<>();

		for (int i = 0; i < 1_000_000; i++) {
			list.add(new Student(i, "name_" + i, new Score(i, i, i)));
		}

		String fileName = "data/studentScore_list.dat";
		long start = System.currentTimeMillis();

		try (FileOutputStream fos = new FileOutputStream(fileName);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(bos);) {

			oos.writeObject(list);

			System.out.println("파일 작성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (FileInputStream fis = new FileInputStream(fileName);
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);) {

			ArrayList<Student> newList = new ArrayList<>();
			ArrayList<?> result = (ArrayList<?>) ois.readObject();

			for (Object object : result) {
				if (object instanceof Student) {
					newList.add((Student) object);
				}
			}
			System.out.println(newList.getFirst());
			System.out.println(newList.getLast());
			long end = System.currentTimeMillis();

			System.out.println("소요 시간: " + (end - start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
