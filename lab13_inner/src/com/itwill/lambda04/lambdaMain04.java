package com.itwill.lambda04;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class lambdaMain04 {

	public static void main(String[] args) {
		// 사원(Employee)들의 리스트
		List<Employee> employees = Arrays.asList(new Employee(101, "김지현", "개발1팀", "과장", 700),
				new Employee(102, "이동준", "개발2팀", "부장", 800), new Employee(103, "이승행", "개발1팀", "대리", 500),
				new Employee(104, "정윤정", "개발2팀", "부장", 1000), new Employee(105, "김동환", "인사팀", "회장", 30000),
				new Employee(106, "노형진", "인사팀", "차장", 900), new Employee(107, "오쌤", "총무팀", "대리", 300));
		// Ex1. 모든 직원들의 정보를 한 줄에 한 명씩 출력
		System.out.println("Ex 1 >=================================================================");
//		employees.stream().map(employee -> employee).forEach(System.out::println);
		employees.forEach(System.out::println);

		// Ex2. 개발(1, 2) 팀에서 일하는 직원들의 급여 합계
		System.out.println("Ex 2 >=================================================================");
		int result1 = employees.stream().filter(e -> e.getDept().equals("개발1팀") || e.getDept().equals("개발2팀"))
				.mapToInt(e -> e.getSalary()).sum();
		System.out.println(result1);

		// Ex3. 개발팀에서 일하는 직원들의 급여 평균
		System.out.println("Ex 3 >=================================================================");
		double result2 = employees.stream().filter(e -> e.getDept().contains("개발")).mapToDouble(e -> e.getSalary())
				.average().orElseThrow(); // 평균을 계산할 수 있으면 double 값을 리턴, 그렇지 않으면 예외를 발생
		System.out.println(result2);

		// Ex4. 직급이 부장인 직원들의 급여 합계
		System.out.println("Ex 4 >=================================================================");
		int result3 = employees.stream().filter(e -> e.getJobTitle().equals("부장")).mapToInt(e -> e.getSalary()).sum();
		System.out.println(result3);

		// Ex5. 직급이 부장인 직원들의 급여 평균
		System.out.println("Ex 5 >=================================================================");
		OptionalDouble result4 = employees.stream().filter(e -> e.getJobTitle().equals("부장"))
				.mapToInt(e -> e.getSalary()).average();
		System.out.println(result4.orElse(0)); // 평균을 계산할 수 있으면 double 값을 리턴, 그렇지 않으면 0을 리턴

		// Ex6. 급여가 1000 이상인 직원들의 정보를 한줄에 한 명씩 출력
		System.out.println("Ex 6 >=================================================================");
		employees.stream().filter(e -> e.getSalary() >= 1000).map(employee -> employee).forEach(System.out::println);

		// Ex7. 개발 1팀 직원들의 급여가 10% 인상하고, 인상된 급여의 평균
		System.out.println("Ex 7 >=================================================================");
		OptionalDouble result5 = employees.stream().filter(e -> e.getDept().equals("개발1팀"))
				.mapToDouble(e -> e.getSalary() * 1.1).average();
		System.out.println(result5.getAsDouble());

		// Ex8. 대리가 몇명인지 출력
		System.out.println("Ex 8 >=================================================================");
		int result6 = (int) employees.stream().filter(e -> e.getJobTitle().equals("대리")).count();
		System.out.println(result6);
	}
}
