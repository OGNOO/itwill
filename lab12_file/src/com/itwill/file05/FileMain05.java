package com.itwill.file05;

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
		for (int i = 0; i < 10; i++) {
			list.add(new Product(i, "name_" + i, i));
		}
		System.out.println("size = " + list.size());
		// ArrayList를 저장하는 파일 이름
		String fileName = "data/product_list.dat";

		// 1. Product 타입 객체 1_000_000 개를 저장하는 ArrayList 를 파일 쓰기
		// FOS, BOS, OOS 을 이용

		try ( // 리소스 생성:
				FileOutputStream out = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(out);) {
			// 파일에 쓸 Product 타입의 객체를 생성

			// 자바 객체를 파일에 씀
			oos.writeObject(list);

			System.out.println("파일 작성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2. 파일에서 객체를 읽어서 ArrayList<Product>로 변환하기
		// FIS, BOS, OOS 을 이용
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			Object list2 = ois.readObject();
			System.out.println(list2);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		String fileName = "data/pd.txt";
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, Charset.forName("UTF-8")))) {
//			bw.write("Alice, 30");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
