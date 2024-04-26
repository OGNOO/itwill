ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY/MM/DD HH24:MI:SS';

/*
 * DDL(Data Definition Language): create, alter, truncate, drop
 * DML(Data Manipulation Language): insert, update, delete
 */
-- create TABLE ... AS SELECT ...
-- 테이블 모양(컬럼 이름, 데이터 타입)과 데이터를 복사
-- EMP 테이블을 EX_EMP6 으로 복사:
CREATE TABLE EX_EMP6 AS
  SELECT *
  FROM EMP;

SELECT *
FROM EX_EMP6;

-- EMP 테이블의 모양과 똑같은 테이블을 생성, 데이터는 복사하지 않음
CREATE TABLE EX_EMP7 AS
  SELECT *
  FROM EMP
  WHERE EMPNO = - 1;

SELECT *
FROM EX_EMP7;

-- truncate TABLE: 테이블의 모든 행을 삭제하는 DDL
SELECT *
FROM EX_EMP6;

TRUNCATE TABLE EX_EMP6;

-- DROP TABLE: 테이블을 삭제하는 DDL
DROP TABLE EX_EMP7;

-- DML
-- INSERT INTO 테이블(컬럼, ...) VALUES (값, ...);
---> 테이블에 1개의 행을 삽입(INSERT)
-- INSERT INTO 테이블 (컬럼, ...) SELECT ...;\
---> SELECT 결과만큼 여러개의 행들을 삽입(INSERT)
SELECT *
FROM EX_EMP6;

INSERT INTO EX_EMP6
  SELECT *
  FROM EMP
  WHERE DEPTNO = 10;

-- EMP 테이블에서 수당(COMM)이 NULL 아닌 행들을 BONUS 테이블에 삽입:
INSERT INTO BONUS
  SELECT ENAME, JOB, SAL, COMM
  FROM EMP
  WHERE COMM IS NOT NULL;

SELECT *
FROM BONUS;

COMMIT;

-- update 문: 테이블 데이터 업데이트(수정)
-- update 테이블 SET 변수 = 값, ... WHERE 조건식;
SELECT *
FROM EMP;

-- EMP 테이블에서 사번이 1004인 직원의 급여를 6000으로 변경하지
UPDATE EMP
SET
  SAL = 6000
WHERE
  EMPNO = 1004;

-- WHERE 조건식이 없으면 UPDATE 문장은 테이블의 모든 행의 값을 업데이트 함
COMMIT;

UPDATE EMP
SET
  SAL = 6000;

SELECT *
FROM EMP;

ROLLBACK;

-- 직전의 COMMIT 상태로 되돌림.
SELECT *
FROM EMP;

UPDATE EMP
SET
  JOB = 'MANAGER', HIREDATE = '2024/04/25'
WHERE
  EMPNO = 1004;

SELECT *
FROM EMP
WHERE EMPNO = 1004;

-- 'ACCOUNTING' 부서에서 일하는 직원들의 급여를 10% 인상:
UPDATE EMP
SET
  SAL = SAL * 1.1
WHERE
  DEPTNO = (
    SELECT DEPTNO
    FROM DEPT
    WHERE DNAME = 'ACCOUNTING'
  );

-- salgrade가 1인 직원들의 급여를 25% 인상:

UPDATE EMP
SET
  SAL = SAL * 1.25
WHERE
  SAL BETWEEN(
    SELECT LOSAL
    FROM SALGRADE
    WHERE GRADE = 1
  )AND(
    SELECT HISAL
    FROM SALGRADE
    WHERE GRADE = 1
  );

SELECT *
FROM EMP
WHERE SAL BETWEEN(
    SELECT LOSAL
    FROM SALGRADE
    WHERE GRADE = 1
  )AND(
    SELECT HISAL
    FROM SALGRADE
    WHERE GRADE = 1
  );

-- delete 문: 테이블에서 행 삭제(DML)
-- DELETE FROM 테이블 WHERE 조건식;
-- WHERE 조건식을 쓰지 않으면 테이블의 모든 행이 삭제됨!

-- EMP 테이블에서 사번 1004인 직원 정보를 삭제:
DELETE FROM EMP
WHERE
  EMPNO = 1004;

--> 모든 행 삭제
DELETE FROM EMP;

ROLLBACK;

-- EMP 테이블에서 1987년에 입사한 사원들을 삭제:
DELETE FROM EMP
WHERE
  TO_CHAR(HIREDATE, 'YYYY') = 1987;