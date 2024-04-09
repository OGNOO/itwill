package com.itwill.map01;

import java.util.HashMap;
import java.util.Set;

// Collection<E>
//  |__List<E>, Set<E>, ...
//    |__ArrayList<E>, LinkedList<E>, ...
// 
// Map<K, V>
//  |__HashMap<K,V>, TreeMap<K,V>, ...
//
// Map<K, V>: 키(Key)와 값(Value)의 쌍으로 구성된 데이터를 저장하는 자료 구조
// (1) Key: 중복된 값을 가질 수 없음. Map 에서 하나의 값을 찾기 위한 인덱스와 비슷한 역할 
//   - 데이터를 저장/검색/수정/삭제 할 때 값을 찾기 위한 용도
//   - 연속된 값을 가질 필요는 없음
// (2) Value: 중복된 값을 가질 수 있음
//
// HashMap<K, V>: Key 를 기준으로 검색을 빠르게 할 수 있는 Map
// TreeMap<K, V>: Key 를 기준으로 정렬을 빠르게 할 수 있는 Map
public class MapMain01 {

	public static void main(String[] args) {
		// 정수를 Key 로 하고, 문자열을 Value 로 하는 HashMap 을 선언, 초기화
		HashMap<Integer, String> map = new HashMap<>();
		System.out.println(map);
		System.out.println("size = " + map.size());

		// map 에 Key-Value 데이터를 저장:
		map.put(1, "홍길동");
		map.put(10, "오쌤");
		map.put(101, "홍길동");

		System.out.println(map);
		// map 에 저장된 값(value)를 찾는 메서드: get(Key)
		String value = map.get(1); // -> Key 가 존재하면 value 를 리턴
		System.out.println(value);

		value = map.get(100); // -> Key 가 없으면 null 을 리턴
		System.out.println(value);

		// getOrDefault(Key, defaultValue)
		value = map.getOrDefault(10, "무명씨"); // -> key 가 존재하면, Key 에 매핑된 Value 를 리턴
		System.out.println(value);

		value = map.getOrDefault(11, "무명씨"); // -> key 가 존재하면, Key 에 아규먼트 defaultValue 를 리턴
		System.out.println(value);

		// keySet(): Map 의 키(Key)들로 이루어진 집합(Set)을 리턴
		Set<Integer> keySet = map.keySet();
		System.out.println(keySet);

		for (Integer k : keySet) {
			System.out.println(k + " : " + map.get(k));
		}

		// Map 에 저장된 데이터 삭제:
		map.remove(101);
		System.out.println(map);

		// Map 에 저장된 Key 와 Value 가 일치해야지만 삭제
		map.remove(1, "?");
		System.out.println(map);

		// put(Key, Value):
		// (1) Key 가 존재하지 않으면, 새로운 Key-Value 쌍의 데이터를 저
		// (2) Key 가 존재하면, 해당 키의 값을 변경
		map.put(10, "No Name");
		System.out.println(map);
	}
}
