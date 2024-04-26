/*
 * 기본 QUERY 문장:
 * SELECT 컬럼이름, ...FROM 테이블 이름:
 * 테이블에서 모든 컬럼을 검색:
 * SELECT * FROM 테이블 이름;
 */
-- emp: 직원 테이블, dept: 부서 테이블
-- 직원 테이블에서 사번(empno)과 직원 이름(ename)을 검색:
SELECT EMPNO, ENAME
FROM EMP;

SELECT ENAME, EMPNO
FROM EMP;

-- 직원 테이블에서 모든 컬럼을 검색:
SELECT *
FROM EMP;

SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO
FROM EMP;

-- 컬럼 이름에 별명(alias) 주기: as "별명"
-- 별명에 공백이 없는 경우(한 단어인 경우)에는 큰 따옴표를 생략 가능
-- 별명이 공백을 포함하는 경우 큰따옴표를 반드시 사용
-- 별명에는 큰따옴표("")만 사용. 작은따옴표는 사용할 수 없음!
-- (주의) SQL에서 문자열은 작은따옴표로 표시: 'SCOTT'
-- 부서 테이블에서 부서번호, 부서이름을 검색:
SELECT DEPTNO AS "부서 번호", DNAME AS "부서 이름"
FROM DEPT;

SELECT DEPTNO AS "부서 번호", DNAME AS
FROM DEPT;

-- 연결 연산자(||): 2개 이상의 컬럼(또는 문자열)을 합쳐서 하나의 컬럼으로 출력
-- '부서번호-부서이름' 형식의 문자열을 "부서"라는 컬럼으로 출력!
SELECT DEPTNO
                                                                                               || '-'
                                                                                               || DNAME AS "부서"
FROM DEPT;

-- 부서 테이블을 검색해서 'A부서는 B에 있습니다.' 형식으로 출력:
SELECT DNAME
                                               || ' 부서는'
                                               || LOC
                                               || ' 에 있습니다' AS "부서 정보"
FROM DEPT;

-- 직원 테이블을 검색해서 'A의 급여는 B' 형식으로 출력:
SELECT *
FROM EMP;

SELECT ENAME
       || ' 의 급여는 '
       || SAL
FROM EMP;

-- 검색 결과를 정렬해서 출력:
-- SELECT 컬럼, ... FROM 테이블 order by 컬럼 [asc/desc];
-- asc: 오름차순 정렬(ascending order). 기본값. asc 생략 가능.
-- desc: 내림차순 정렬(desending order).

-- 부서 테이블에서 부서 이름을 오름차순 정렬 출력
SELECT DEPTNO, DNAME
FROM DEPT;

-- 부서 테이블에서 부서 이름을 내림차순 정렬 출력
SELECT DEPTNO, DNAME
FROM DEPT
ORDER BY DNAME DESC;

-- 1. 직원 테이블에서 사번, 이름, 급여를 검색. 사번 오름차순 정렬.
SELECT EMPNO, ENAME, SAL
FROM EMP
ORDER BY SAL ASC;

-- 2. 직원 테이블에서 사번, 이름, 급여를 검색. 사번 내림차순 정렬.
SELECT EMPNO, ENAME, SAL
FROM EMP
ORDER BY SAL DESC;

-- 3. 직원 테이블에서 부서번호, 사번, 이름 검색.
-- 정렬 순서: (1) 부서 번호 오름차순, (2) 사번 오름차순
SELECT DEPTNO, EMPNO, ENAME
FROM EMP
ORDER BY DEPTNO ASC, EMPNO ASC;

-- 4. 직원 테이블에서 부서번호, 사번, 이름, 급여 검색.
-- 정렬 순서: (1) 부서 번호 오름차순, (2) 급여 오름차순
SELECT DEPTNO, EMPNO, ENAME, SAL
FROM EMP
ORDER BY DEPTNO ASC, SAL DESC;

-- 중복되지 않는 결과 출력:
SELECT JOB
FROM EMP;

--> 14개 행(record)
SELECT DISTINCT JOB
FROM EMP;

--> 5개 행
SELECT DISTINCT JOB
FROM EMP
ORDER BY JOB;

-- 중복되지 않는 부서번호, 업무 출력. (1)부서번호 오름차순, (2) 업무 오름차순:
SELECT DISTINCT DEPTNO, JOB
FROM EMP
ORDER BY DEPTNO, JOB;