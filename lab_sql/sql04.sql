/*
 * 오라클 함수(FUNCTION)
 * 1. 단일 행 함수:
 *  행(row)이 하나씩 함수의 아규먼트로 전달되고,
 *  행 마다 하나씩 결과가 리턴되는 함수.
 *  (예) TO_DATE, TO_CHAR, LOWER, UPPER, ...
 * 2. 다중 행 함수:
 *  여러개의 행들이 함수의 아규먼트로 전달되고, 하나의 결과가 리턴되는 함수
 *  (예) 통계 관련 함수: COUNT, SUM, MAX, MIN, VARIANCE(분산), STDDEV(표준편차), ...
 */
-- 단일 행 함수 예 - 모든 행의 문자열을 소문자로 바꾸기.
SELECT ENAME, LOWER(ENAME), JOB, LOWER(JOB)
FROM EMP;

-- TO_CHAR(...): 다른 타입의 값을 문자열로 변환. (예) 날짜 -> 문자열
ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY/MM/DD HH24:MI:SS';

SELECT HIREDATE, TO_CHAR(HIREDATE, 'YYYY/MM/DD'), TO_CHAR(HIREDATE, 'MM-DD-YYYY')
FROM EMP;

-- nvl(변수, 값): 변수가 null 이면 값을 리턴하고, null 아니면 원래 값을 리턴
-- nvl2(변수, 값1, 값2): 변수가 null 이 아니면 값1을 리턴하고,
-- 변수가 null 이면 원래 값2를 리턴
SELECT COMM, NVL(COMM, 0), NVL2(COMM, COMM, 0), NVL2(COMM, 'T', 'F')
FROM EMP;

-- 직원 이름이 (대/소문자 구분없이) 'a'로 시작하는 직원들의 모든 정보
SELECT *
FROM EMP
WHERE ENAME LIKE 'a%'
  OR ENAME LIKE 'A%';

SELECT *
FROM EMP
WHERE LOWER(ENAME)LIKE 'a%';

-- 사번, 이름, 급여, 수당, 연봉을 검색.
-- 연봉 = 급여 * 12 + 수당.
SELECT EMPNO, ENAME, SAL, SAL * 12 + NVL(COMM, 0)AS 연봉
FROM EMP;

-- 다중 행 함수 예:
-- COUNT(컬럼): null 이 아닌 행의 개수를 리턴.
SELECT COUNT(EMPNO), COUNT(MGR), COUNT(COMM)
FROM EMP;

-- 테이블의 행의 개수
SELECT COUNT( * )
FROM EMP;

-- 통계 함수: 합계, 평균, 최댓값, 최솟값, 분산, 표준편차
SELECT SUM(SAL),    ROUND(AVG(SAL), 2), MAX(SAL), MIN(SAL), VARIANCE(SAL),
  STDDEV(SAL)
FROM EMP;

-- 단일 행 함수와 다중 행 함수는 함께 사용할 수 없음.
-- SELECT SAL, SUM(SAL) FROM EMP;

--> 모든 통계 함수들은 NULL 을 제외하고 계산을 함.
SELECT SUM(COMM), AVG(COMM)
FROM EMP;

-- sysout, console.log 랑 비슷하게 sql 에서 사용할 수 있는 문법
SELECT UPPER('Allen')
FROM DUAL;

SELECT INITCAP('AEQWQ' /*CHAR*/)
FROM DUAL;

/*
 * 그룹별 쿼리(QUERY)
 * (예) 부서별 직원수, 부서별 급여 평균, ...
 * (문법)
 * SELECT 컬럼, 함수호출, ...
 * FROM 테이블
 * WHERE 조건식(1)
 * GROUP BY 컬럼(그룹을 묶을 수 있는 변수)
 * HAVING 조건식(2)
 * ORDER BY 컬럼(정렬 기준), ...;
 * 
 * 조건식(1): 그룹으로 묶기 전에 행들을 선택할 조건
 * 조건식(2): 그룹으로 묶은 후에 행들을 선택할 조건
 */

-- 부서별 급여 평균
SELECT DEPTNO, ROUND(AVG(SAL), 2)AS AVG_SAL
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO;

-- 부서별 급여 평균, 표준편차
SELECT DEPTNO, ROUND(AVG(SAL), 2)AS AVG_SAL, ROUND(STDDEV(SAL), 2)AS STD_SAL
FROM EMP
GROUP BY DEPTNO
ORDER BY DEPTNO;

-- 모든 문제에서 소수점은 반올림해서 소수점 이하 2자리까지 표시.
-- Ex. 업무별 업무, 직원수, 급여 최댓값, 최솟값, 평균을 업무 오름차순으로 출력.
SELECT JOB, COUNT(JOB)AS 직원수, MAX(SAL)AS "급여 최댓값", MIN(SAL)AS "급여 최솟값", ROUND(AVG(SAL), 2)AS "급여 평균"
FROM EMP
GROUP BY JOB
ORDER BY JOB;

-- Ex. 부서별/업무별로 부서번호, 업무, 직원수, 급여 평균을 검색.
--     정렬 순서: (1) 부서번호 (2) 업무
SELECT DEPTNO, JOB, COUNT( * )AS 직원수, ROUND(AVG(SAL), 2)AS "급여 평균"
FROM EMP
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO, JOB;

-- Ex. 입사연도별 사원수를 검색. (힌트) to_char(날짜, 포맷) 이용.
SELECT TO_CHAR(HIREDATE, 'YYYY')AS 연도, COUNT( * )AS 사원수
FROM EMP
GROUP BY TO_CHAR(HIREDATE, 'YYYY')
ORDER BY 연도;

-- Ex. 부서별 급여 평균 검색. 급여 평균이 2000 이상인 부서만 검색.
SELECT DEPTNO AS 부서번호, ROUND(AVG(SAL), 2)AS "급여 평균"
FROM EMP
GROUP BY DEPTNO
HAVING AVG(SAL) >= 2000
ORDER BY DEPTNO;

-- Ex. mgr 컬럼이 null이 아닌 직원들 중에서 부서별 급여 평균을 검색.
--     정렬순서: 부서번호 오름차순.
SELECT DEPTNO AS 부서번호, ROUND(AVG(SAL), 2)AS "급여 평균"
FROM EMP
WHERE MGR IS NOT NULL
GROUP BY DEPTNO
ORDER BY DEPTNO;

-- PRESIDENT 는 제외하고, 업무별 사원수를 검색.
-- 업무별 사원수가 3명 이상인 업무들만 검색. 업무이름 오름차순.
SELECT JOB, COUNT( * )AS "직원수"
FROM EMP
WHERE JOB NOT IN('PRESIDENT')
GROUP BY JOB
HAVING COUNT( * ) >= 3
ORDER BY JOB;

SELECT JOB, COUNT( * )AS "직원수"
FROM EMP
GROUP BY JOB
HAVING COUNT( * ) >= 3
  AND JOB NOT IN('PRESIDENT')
ORDER BY JOB;

-- 입사연도, 부서별 사원수 검색. 1980년은 검색에서 제외.
-- 연도별, 부서별 사원수가 2명 이상인 경우만 출력.
-- 정렬순서: (1) 연도, (2) 부서
SELECT TO_CHAR(HIREDATE, 'YYYY')AS 입사연도, DEPTNO, COUNT( * )AS 직원수
FROM EMP
GROUP BY TO_CHAR(HIREDATE, 'YYYY'), DEPTNO
HAVING TO_CHAR(HIREDATE, 'YYYY') != 1980
  AND COUNT( * ) >= 2
ORDER BY 입사연도, DEPTNO;

SELECT TO_CHAR(HIREDATE, 'YYYY')AS 입사연도, DEPTNO, COUNT( * )AS 직원수
FROM EMP
WHERE TO_CHAR(HIREDATE, 'YYYY') != 1980
GROUP BY TO_CHAR(HIREDATE, 'YYYY'), DEPTNO
HAVING COUNT( * ) >= 2
ORDER BY 입사연도, DEPTNO;