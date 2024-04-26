ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY/MM/DD HH24:MI:SS';

/*
 * 서브쿼리(SUBQUERY): SQL 문장의 일부로 다른 SQL 문장이 포함되는 것
 */
-- 급여가 평균 이상인 직원들?
SELECT AVG(SAL)
FROM EMP;

SELECT *
FROM EMP
WHERE SAL >= 2073.21;

SELECT *
FROM EMP
WHERE SAL >= (
    SELECT AVG(SAL)
    FROM EMP
  );

-- ALLEN 보다 더 적은 급여를 받는 직원들의 사번, 이름, 급여를 검색
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL < (
    SELECT SAL
    FROM EMP
    WHERE ENAME = 'ALLEN'
  );

-- ALLEN 과 같은 업무를 담당하는 직원들의 사번, 이름, 부서번호, 업무, 급여를 검색.
SELECT EMPNO, ENAME, DEPTNO, JOB, SAL
FROM EMP
WHERE JOB = (
    SELECT JOB
    FROM EMP
    WHERE ENAME = 'ALLEN'
  );

-- SALESMAN 의 급여 최댓값보다 더 많은 급여를 받는 직원들의 사번, 이름, 급여, 업무를 검색.

SELECT EMPNO, ENAME, SAL, JOB
FROM EMP
WHERE SAL > (
    SELECT MAX(SAL)
    FROM EMP
    WHERE JOB = 'SALESMAN'
  );

-- Ex. WARD의 연봉보다 더 많은 연봉을 받는 직원들의 이름, 급여, 수당, 연봉을 검색.
-- 연봉 = sal * 12 + comm. comm(수당)이 null인 경우에는 0으로 계산.
-- 연봉 내림차순 정렬.
SELECT ENAME, SAL, NVL(COMM, 0), SAL * 12 + NVL(COMM, 0)AS 연봉
FROM EMP
WHERE SAL * 12 + NVL(COMM, 0) > (
    SELECT SAL * 12 + NVL(COMM, 0)
    FROM EMP
    WHERE ENAME = 'WARD'
  )
ORDER BY 연봉 DESC;

-- Ex. SCOTT과 같은 급여를  받는 직원들의 이름과 급여를 검색.
SELECT ENAME, SAL
FROM EMP
WHERE SAL = (
    SELECT SAL
    FROM EMP
    WHERE ENAME = 'SCOTT'
  );

-- Ex. 위 결과에서 SCOTT은 제외하고 검색.
SELECT ENAME, SAL
FROM EMP
WHERE SAL = (
    SELECT SAL
    FROM EMP
    WHERE ENAME = 'SCOTT'
  )
  AND ENAME NOT IN('SCOTT');

-- Ex. ALLEN보다 늦게 입사한 직원들의 이름, 입사날짜를 최근입사일부터 출력.
SELECT ENAME, TO_CHAR(HIREDATE, 'YYYY/MM/DD')AS 입사일
FROM EMP
WHERE TO_CHAR(HIREDATE, 'YYYY') > (
    SELECT TO_CHAR(HIREDATE, 'YYYY')
    FROM EMP
    WHERE ENAME = 'ALLEN'
  )
ORDER BY 입사일 DESC;

-- Ex. 매니저가 KING인 직원들의 사번, 이름, 매니저 사번을 검색.
SELECT EMPNO, ENAME, MGR
FROM EMP
WHERE MGR = (
    SELECT EMPNO
    FROM EMP
    WHERE ENAME = 'KING'
  );

-- Ex. ACCOUNTING 부서에 일하는 직원들의 이름, 급여, 부서번호를 검색.
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = (
    SELECT DEPTNO
    FROM DEPT
    WHERE DNAME = 'ACCOUNTING'
  );

-- Ex. CHICAGO에서 근무하는 직원들의 이름, 급여, 부서 번호를 검색.
SELECT ENAME, SAL, DEPTNO
FROM EMP
WHERE DEPTNO = (
    SELECT DEPTNO
    FROM DEPT
    WHERE LOC = 'CHICAGO'
  );