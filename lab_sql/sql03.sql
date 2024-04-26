/*
 * 조건 검색 where
 *
 * 테이블에서 데이터 검색:
 * (1) projection: 테이블에서 원하는 컬럼(들)을 선택.
 * (2) selection: 테이블에서 조건을 만족하는 행(들)을 검색.
 *
 * 문법: select 컬럼, ... from 테이블 where 조건식 order by 컬럼, ...;
 * 조건식에서 사용되는 연산자들:
 *   (1) 비교 연산자: =, !=, >, >=, <, <=, is null, is not null, ...
 *   (2) 논리 연산자: and, or, not
 */

-- 직원 테이블에서 10번 부서에서 근무하는 직원들의 부서번호, 사번, 이름 출력.
-- 사번 오름차순 정렬.
SELECT DEPTNO, EMPNO, ENAME
FROM EMP
WHERE DEPTNO = 10
ORDER BY EMPNO;

-- 직원 테이블에서 수당(comm)이 null이 아닌 직원들의
-- 사번, 부서번호, 이름, 급여, 수당을 출력. 사번 오름차순 정렬.
SELECT EMPNO, DEPTNO, ENAME, SAL, COMM
FROM EMP
WHERE COMM IS NOT NULL
ORDER BY EMPNO;

--> SQL에서 null 여부 비교를 할 때는 =, != 사용할 수 안됨!
--> SQL에서 null 여부 비교를 할 때는 반드시 is null, is not null을 사용!

-- 직원 테이블에서 급여가 2000 이상인 직원들의 이름, 업무, 급여를 출력.
-- 급여 내림차순 정렬.
SELECT ENAME, JOB, SAL
FROM EMP
WHERE SAL >= 2000
ORDER BY SAL DESC;

-- 직원 테이블에서 급여가 2000 이상 3000 이하인 직원들의
-- 이름, 업무, 급여를 검색. 급여 내림차순 정렬.
SELECT ENAME, JOB, SAL
FROM EMP
WHERE SAL >= 2000
  AND SAL <= 3000
ORDER BY SAL DESC;

--> where 2000 <= sal <= 3000 (X)
--> 논리 연산자로 & 또는 &&를 사용할 수 없음.

SELECT ENAME, JOB, SAL
FROM EMP
WHERE SAL BETWEEN 2000 AND 3000
ORDER BY SAL DESC;

-- 직원 테이블에서 10번 또는 20번 부서에서 근무하는 직원들의
-- 부서번호, 이름, 급여를 검색. 부서번호 오름차순.
SELECT DEPTNO, ENAME, SAL
FROM EMP
WHERE DEPTNO = 10
  OR DEPTNO = 20
ORDER BY DEPTNO;

--> where deptno = 10 or 20 (X)

SELECT DEPTNO, ENAME, SAL
FROM EMP
WHERE DEPTNO IN (10, 20)
ORDER BY DEPTNO;

-- 직원 테이블에서 업무가 'CLERK'인  직원들의
-- 업무, 이름, 급여를 출력. 정렬 순서: 이름.
SELECT JOB, ENAME, SAL
FROM EMP
WHERE JOB = 'CLERK'
ORDER BY ENAME;

--> SQL에서는 문자열을 비교할 때 =, != 연산자를 사용.
--> SQL에서 문자열을 작은따옴표('')를 사용. 대/소문자를 구분.

-- 직원 테이블에서 업무가 'CLERK' 또는 'MANAGER'인  직원들의
-- 업무, 이름, 급여를 검색. 정렬 순서: (1) 업무, (2) 급여.
SELECT JOB, ENAME, SAL
FROM EMP
WHERE JOB = 'CLERK'
  OR JOB = 'MANAGER'
ORDER BY JOB, SAL;

--> where job = 'CLERK' or 'MANAGER' (X)

SELECT JOB, ENAME, SAL
FROM EMP
WHERE JOB IN ('CLERK', 'MANAGER')
ORDER BY JOB, SAL;

-- 직원 테이블에서 20번 부서에서 근무하는 CLERK의
-- 모든 정보(컬럼)을 검색.
SELECT *
FROM EMP
WHERE DEPTNO = 20
  AND JOB = 'CLERK';

-- 직원 테이블에서 CLERK, ANALYST, MANAGER가 아닌  직원들의
-- 사번, 이름, 업무, 급여를 검색. 정렬 순서: 사번.
SELECT EMPNO, ENAME, JOB, SAL
FROM EMP
WHERE JOB != 'CLERK'
  AND JOB != 'ANALYST'
  AND JOB != 'MANAGER'
ORDER BY EMPNO;

SELECT EMPNO, ENAME, JOB, SAL
FROM EMP
WHERE JOB NOT IN ('CLERK', 'ANALYST', 'MANAGER')
ORDER BY EMPNO;

-- 숫자 타입 뿐만 아니라, 문자열과 날짜 타입도 대소비교가 가능.
-- (예) 'a' < 'b', 2024/04/21 < 2024/04/22

-- '1987/01/01' 이후에 입사한 직원들의 모든 정보(컬럼)을 출력.
-- 정렬 순서: 입사일 오름차순.

/*
 * output 날짜 포멧 설정
 */
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY/MM/DD HH24:MI:SS';

SELECT *
FROM EMP
WHERE HIREDATE > '1987-01-01'
ORDER BY HIREDATE;

-- 문자열로 비교할 때 vscode output 이랑 달라서 에러남.
-- 해결법은 아래와 같음.

--> 날짜 타입을 비교할 때, '1987/01/01' 문자열 타입이 날짜 타입으로 변환
--> 암묵적(자동) 타입 변환:
-- 오라클은 hiredate 컬럼(date 타입)과 문자열 '1987/01/01'의 크기를 비교할 때
-- 날짜 타입을 문자열로 변환한 후 문자열의 크기 비교를 수행함.

SELECT *
FROM EMP
WHERE HIREDATE > TO_DATE('1987-01-01', 'YYYY-MM-DD')
ORDER BY HIREDATE;

--> 명시적 타입 변환:
-- TO_DATE('문자열','YYYY-MM-DD')을 지정된 형식에 맞는 날짜(date) 타입으로 변환.

-- like 검색: 특정 문자열로 시작하거나, 특정 문자열이 포함된 값을 찾는 검색.
-- 이름이 'A'로 시작하는 직원들의 모든 정보:
SELECT *
FROM EMP
WHERE ENAME LIKE 'A%';

-- like 검색에서 사용하는 wildcard:
-- (1) %: 글자 수 제한 없음.
-- (2) _(underscore, 밑줄): 밑줄이 있는 자리에 어떤 문자가 있어도 됨.
SELECT *
FROM EMP
WHERE JOB LIKE '_LERK';

-- 이름에 'A'가 포함된 직원들의 모든 정보:
SELECT *
FROM EMP
WHERE ENAME LIKE '%A%';

-- 30번 부서에 근무하고, 업무이름에 'SALES'가 포함되어 있는 직원들의
-- 사번, 부서번호, 이름, 업무, 급여, 수당을 출력. 사번 오름차순 정렬.
SELECT EMPNO, DEPTNO, ENAME, JOB, SAL, COMM
FROM EMP
WHERE DEPTNO = 30
  AND JOB LIKE '%SALES%'
ORDER BY EMPNO;