package com.itwill.file03;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

// 파일 ===> FileInputStream ===> BufferInputStream ===> 프로그램 
// FIS: HDD 에 있는 파일을 직접 접근해서 데이터를 읽고, 메모리(RAM)에 적재
// BIS: 메모리(RAM)에 있는 파일 내용을 읽는(read) 메서드를 제공
//
// 파일 <=== FileOutputStream <=== BufferOutputStream <=== 프로그램
// FOS: HDD 에 있는 파일에 데이터를 씀
// BOS: 메모리(RAM)에 있는 데이터를 쓰는(write) 메서드 제공

public class FileMain03 {

	public static void main(String[] args) {
		// BIS, BOS 을 사용한 파일 읽기, 쓰기:

		String origin = "data/ratings.dat";
		String dest = "data/ratings_copy2.dat";

		FileInputStream in = null;
		BufferedInputStream bin = null;
		FileOutputStream out = null;
		BufferedOutputStream bout = null;
		try {
			in = new FileInputStream(origin);
			bin = new BufferedInputStream(in);
//			bin = new BufferedInputStream(new FileInputStream(origin));

			out = new FileOutputStream(dest);
			bout = new BufferedOutputStream(out);
//			bout = new BufferedOutputStream(new FileOutputStream(dest));

			long start = System.currentTimeMillis();
			while (true) {
				byte[] buffer = new byte[4 * 1024];
				int b = in.read(buffer);

				if (b == -1) {
					break;
				}
				bout.write(buffer, 0, b);
			}
			long end = System.currentTimeMillis();
			System.out.println("복사 경과 시간 : " + (end - start) + "ms");
		} catch (Exception e) {
			// 파일 못찾음
			e.printStackTrace();
		} finally {
			// 리소스 해제: 나중에 만들어진 스트림 객체를 먼저 close 하고,
			// 먼저 만들어진 스트림 객체를 나중에 close 해야 함
			// 스트림 객체들은 생성된 순서의 반대로 close 메서드를 호출해야 함
			// 가장 마지막에 생성된 스트림 객체만 close 하면 나머지는 자동으로 close 됨

			try {
				bout.close();
//				out.close(); 
				bin.close();
//				in.close(); 
				// close 성공
			} catch (Exception e) {
				e.printStackTrace();
				// close 실패
			}
		}

	}

}
