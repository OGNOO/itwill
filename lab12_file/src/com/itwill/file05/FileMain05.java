package com.itwill.file05;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.itwill.file04.Product;

public class FileMain05 {

	public static void main(String[] args) {
		// 파일에 쓸(write) 더미 데이터
		ArrayList<Product> list = new ArrayList<>();

		for (int i = 0; i < 1_000_000; i++) {
			list.add(new Product(i, "name_" + i, i));
		}

		System.out.println("size = " + list.size());
		// ArrayList를 저장하는 파일 이름
		String fileName = "data/product_list.dat";

		// 1. Product 타입 객체 1_000_000 개를 저장하는 ArrayList 를 파일 쓰기
		// FOS, BOS, OOS 을 이용
		long start = System.currentTimeMillis();
		try (FileOutputStream fos = new FileOutputStream(fileName);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(bos);) {

			oos.writeObject(list);

			System.out.println("파일 작성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2. 파일에서 객체를 읽어서 ArrayList<Product>로 변환하기
		// FIS, BIS, OIS 을 이용
		try (FileInputStream fis = new FileInputStream(fileName);
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);) {

			ArrayList<Product> newList = new ArrayList<Product>();
			ArrayList<?> result = (ArrayList<?>) ois.readObject();
			for (Object object : result) {
				if (object instanceof Product) {
					newList.add((Product) object);
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
