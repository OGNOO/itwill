/*
 * ALTER TABLE: 테이블 변경
 * (1) 이름 변경: ALTER TABLE ... RENAME ... TO ...
 * (2) 추가: ALTER TABLE ... ADD ...
 * (3) 삭제: ALTER TABLE ... DROP ...
 * (4) 수정: ALTER TABLE ... MODIFY ...
 */

-- 1. 이름 변경:
SELECT TABLE_NAME
FROM USER_TABLES;

-- 접속 계정의 테이블 이름
-- EX_STUDENTS 테이블을 STUDENTS로 이름 변경
ALTER TABLE EX_STUDENTS RENAME TO STUDENTS;

-- (2) 테이블 컬럼 이름 변경
DESCRIBE STUDENTS; -- 오라클의 테이블 설명 명령어.

-- STUNO 컬럼을 STUID 로 이름 변경:
ALTER TABLE STUDENTS RENAME COLUMN STUNO TO STUID;

-- (3) 제약조건 이름 변경
SELECT CONSTRAINT_NAME
FROM USER_CONSTRAINTS;

-- 제약조건 EX_EMP4_FK1 를 EMP4_FK 로 이름 변경
ALTER TABLE EX_EMP4 RENAME CONSTRAINT EX_EMP4_FK1 TO EMP4_FK;

-- 추가(ADD)
-- (1) 제약조건 추가
-- STUDENTS 테이블의 STUID 컬럼을 PK 로 설정
ALTER TABLE STUDENTS ADD CONSTRAINT STUDENTS_PK PRIMARY KEY(STUID);

-- (2) 컬럼 추가
ALTER TABLE STUDENTS ADD DEPARTMENT NUMBER(2);

-- 삭제(DROP)
-- (1) 제약조건 삭제
ALTER TABLE STUDENTS DROP CONSTRAINT STUDENTS_PK;

COMMIT;

-- (2) 컬럼 삭제
ALTER TABLE STUDENTS DROP COLUMN DEPARTMENT;

-- 수정(MODIFY): 컬럼 정의(데이터 타입, 기본값, NULL 여부)를 수정
-- STUDENTS 테이블의 STUNAME 컬럼의 데이터 타입을
-- VARCHAR2(4 CHAR)에서 VARCHAR2(40 CHAR)로 변경 & NN 추가
ALTER TABLE STUDENTS MODIFY STUNAME VARCHAR2(40 CHAR)NOT NULL;

-- MODIFY 는 제약조건의 내용을 변경하지 못함.
-- 제약조건 삭제 (ALTER TABLE ... DROP CONSTRAINT ...)
--> 제약조건 추가 (ALTER TABLE ... ADD CONSTRAINT ...)