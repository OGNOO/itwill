ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY/MM/DD HH24:MI:SS';

/*
 * SQL 종류:
 * 1. DQL(Data Query Language) : select
 * 2. DML(Data Manipulation Language): insert, update, delete.
 * 3. DDL(Data Definition Language): create, alter, truncate, drop.
 * 4. TCL(Transaction Control Language): 
 *  - commit: 데이터베이스의 변경 내용을 영구 저장.
 *  - rollback: 직전의 commit 상태로 되돌리기.
 * 
 * 테이블 생성:
 * CREATE TABLE 테이블이름(
 *  컬럼이름 데이터타입 [제약조건 기본값],
 * ...
 * );
 * 
 * 컬럼의 데이터 타입으로 사용되는 키워드 데이터베이스 종류에 따라서 다름.
 * 오라클 데이터 타입: NUMBER, VACHAR2, DATE, TIMESTAMP, CLOB, BLOB, ...
 */

/*
 * 테이블 이름: EX_STUDENTS
 * 컬럼:
 *  - STUNO: 학생 번호(학번), 숫자(6자리)
 *  - STUNAME: 학생 이름, 문자열(4글자)
 *  - BIRTHDAY: 생일, 날짜
 */
CREATE TABLE EX_STUDENTS(
  STUNO NUMBER(6), STUNAME VARCHAR2(4 CHAR), BIRTHDAY DATE
);

/* 
 * 테이블에 행 추가(INSERT, 삽입)
 * INSERT INTO 테이블 (컬럼, ...) VALUES(값, ...);
 * 
 * 테이블에 삽입하는 값의 개수가 컬럼의 개수와 같고,
 * 값들의 순서가 테이블 생성시 컬럼 순서와 같으면
 * INSERT INTO 테이블 VALUES(값, ...);
 */
SELECT *
FROM EX_STUDENTS;

INSERT INTO EX_STUDENTS VALUES(
  241001, '홍길동', '2000/12/31'
);

INSERT INTO EX_STUDENTS(
  STUNO, STUNAME
)VALUES(
  241002, '오쌤'
);

--> 실행 중 오류: 'ABCD' 문자열은 학번(숫자 타입)이 될 수 없기 때문.
INSERT INTO EX_STUDENTS VALUES(
  'ABCD', '홍길동', '2000/12/31'
);

--> 실행 중 오류: 테이블의 컬럼 개수와 VALUES의 값들의 개수가 다르기 때문.
INSERT INTO EX_STUDENTS VALUES(
  1, 'ABCD'
);

--> 테이블 이름 뒤에 컬럼 이름을 쓰는 경우, 테이블의 컬럼 순서를 지킬 필요는 없음.
-- 컬럼 순서와 VALUES 의 순서를 맞춰주면 됨.
INSERT INTO EX_STUDENTS(
  STUNAME, STUNO, BIRTHDAY
)VALUES(
  'ABCD', 1, SYSDATE
);

--> 실행 중 오류: STUNO 컬럼의 숫자 자리수보다 큰 값이기 때문.
INSERT INTO EX_STUDENTS(
  STUNO
)VALUES(
  1234567
);

--> 실행 중 오류: STUNAME 컬럼은 최대 4글자(CHAR)까지만 저장할 수 있기 때문.
INSERT INTO EX_STUDENTS(
  STUNAME
)VALUES(
  '가나다라마'
);

--> 현재까지의 INSERT 내용을 DB 테이블에 영구 저장.
COMMIT;

-- 한글 VS 영문자 BYTE 비교
CREATE TABLE EX_TEST_BYTE(
  CTEST VARCHAR2(4 BYTE)
);

INSERT INTO EX_TEST_BYTE VALUES(
  'abcd'
);

--> 성공
INSERT INTO EX_TEST_BYTE VALUES(
  '한글'
);

--> 실패
-- 오라클에서 문자열 저장할 때 인코딩: UTF-8
-- UTF-8일 때, 영문자/숫자/특수기호 1byte, 한글 3byte.

-- 테이블을 생성할 때 컬럼에 기본값 설정하기:
CREATE TABLE EX_TEST_DEFAULT(
  TNO NUMBER(4), TDATE DATE DEFAULT SYSDATE
);

INSERT INTO EX_TEST_DEFAULT VALUES(
  1, '2024-03-14'
);

INSERT INTO EX_TEST_DEFAULT(
  TDATE
)VALUES(
  '2024-03-15'
);

--> tno에는 null이 삽입됨.
INSERT INTO EX_TEST_DEFAULT(
  TNO
)VALUES(
  1234
);

--> tdate에는 null 대신 기본값 sysdate(현재 시간)가 삽입됨.
SELECT *
FROM EX_TEST_DEFAULT;

COMMIT;

/*
 * 테이블을 생성할 때 제약조건(constraint) 만들기:
 * (1) primary key(PK, 고유키)
 * (2) not null(NN)
 * (3) unique
 * (4) check
 * (5) foreign key(FK, 외래키)
 */

-- 테이블 생성할 때 제약조건 만들기 1: 제약조건 이름 설정하지 않기.
CREATE TABLE EX_EMP1(
  ENO NUMBER(4)PRIMARY KEY, -- 사번. 고유키.
  ENAME VARCHAR2(10)NOT NULL, -- 이름.
  EMAIL VARCHAR2(100)UNIQUE, -- 이메일
  AGE NUMBER(3)CHECK(AGE >= 0), MEMO VARCHAR2(1000)
);

INSERT INTO EX_EMP1 VALUES(
  1001, '홍길동', 'hdg@itiwll.com', 16, '안녕하세요'
);

INSERT INTO EX_EMP1(
  ENO, ENAME
)VALUES(
  1002, '허균'
);

--> 성공
INSERT INTO EX_EMP1(
  ENO, ENAME
)VALUES(
  1002, 'abc'
);

--> 실패: PK 제약조건 위배

INSERT INTO EX_EMP1(
  ENO
)VALUES(
  1003
);

--> NN 제약조건 위배

INSERT INTO EX_EMP1(
  ENO, ENAME, EMAIL
)VALUES(
  1003, 'John Doe', 'hdg@itwill.com'
);

INSERT INTO EX_EMP1(
  ENO, ENAME, EMAIL
)VALUES(
  1004, 'John Doe', 'hdg@itwill.com'
);

--> unique 제약조건 위배

INSERT INTO EX_EMP1(
  ENO, ENAME, AGE
)VALUES(
  1005, 'Scott', - 1
);

--> check (age >= 0) 제약 조건 위배

SELECT *
FROM EX_EMP1;

COMMIT;

-- 제약조건 만들기 2: 제약조건 이름 설정
CREATE TABLE EX_EMP2(
  ID NUMBER(4)CONSTRAINT EX_EMP2_PK_ID PRIMARY KEY, ENAME VARCHAR2(10)CONSTRAINT EX_EMP2_NN_ENAME NOT NULL, EMAIL VARCHAR2(100)CONSTRAINT EX_EMP2_UQ_EMAIL UNIQUE, AGE NUMBER(3)CONSTRAINT EX_EMP2_CK_AGE CHECK(AGE >= 0), MEMO VARCHAR2(1000)
);

INSERT INTO EX_EMP2(
  ID, ENAME
)VALUES(
  1, '홍길동'
);

INSERT INTO EX_EMP2(
  ID, ENAME
)VALUES(
  1, '오쌤'
);

--> PK 위배

SELECT *
FROM EX_EMP2;

COMMIT;

-- 제약조건 만들기 3: 컬럼 정의(데이터 타입 선언) 따로, 제약조건 정의 따로.
CREATE TABLE EX_EMP3(
 -- 컬럼 정의: 컬럼 이름 & 데이터 타입
  ID NUMBER(4),                             ENAME VARCHAR2(10),                                   EMAIL VARCHAR2(100),                       AGE NUMBER(3),                             MEMO VARCHAR2(1000),
 -- 제약 조건 정의
  CONSTRAINT EX_EMP3_PK_ID PRIMARY KEY(ID), CONSTRAINT EX_EMP3_NN_ENAME CHECK(ENAME IS NOT NULL), -- 주의!
  CONSTRAINT EX_EMP3_UQ_EMAIL UNIQUE(EMAIL), CONSTRAINT EX_EMP3_CK_AGE CHECK(AGE >= 0)
);

INSERT INTO EX_EMP3(
  ID
)VALUES(
  1234
);

--> check(not null) 위배

-- FK(Foreign Key, 외래키): 다른 테이블의 PK를 참조하는 제약조건.
-- 데이터를 insert할 때, 다른 테이블의 PK에 없는 값이 삽입되지 않도록.
-- 테이블을 만들 때 FK를 설정하려면, PK가 설정된 다른 테이블이 먼저 생성되어 있어야 함.

CREATE TABLE EX_DEPT(
  DEPTNO NUMBER(2)CONSTRAINT EX_DEPT_PK_DEPTNO PRIMARY KEY, DNAME VARCHAR(10)CONSTRAINT EX_DEPT_NN_DNAME NOT NULL
);

CREATE TABLE EX_EMP4(
  EMPNO NUMBER(4)CONSTRAINT EX_EMP4_PK_EMPNO PRIMARY KEY, ENAME VARCHAR2(10)CONSTRAINT EX_EMP4_NN_ENAME NOT NULL, DEPTNO NUMBER(2)CONSTRAINT EX_EMP4_FK_DEPTNO REFERENCES EX_DEPT(DEPTNO)
);

INSERT INTO EX_EMP4 VALUES(
  1200, '오쌤', 10
);

--> 10번 부서가 ex_dept 테이블에 없는 경우에는 insert 실패! FK 위배.
--> 10번 부서가 ex_dept 테이블에 있는 경우에는 insert 성공!

INSERT INTO EX_DEPT VALUES(
  10, '개발1팀'
);

INSERT INTO EX_EMP4(
  EMPNO, ENAME
)VALUES(
  1300, '홍길동'
);

--> FK 제약조건이 있는 컬럼에는 null 삽입될 수도 있음.

INSERT INTO EX_EMP4 VALUES(
  1400, 'Jake', 10
);

--> FK 제약조건이 있는 컬럼에는 중복된 값이 삽입될 수도 있음.

SELECT *
FROM EX_DEPT;

SELECT *
FROM EX_EMP4;

-- 컬럼 정의 따로, FK 제약조건 설정 따로
CREATE TABLE EX_EMP5(
 -- 컬럼 정의:
  EMPNO NUMBER(4),                                                            ENAME VARCHAR2(10), DEPTNO NUMBER(2),
 -- 제약조건 정의:
  CONSTRAINT EX_EMP5_PK_EMPNO PRIMARY KEY(EMPNO), CONSTRAINT EX_EMP5_NN_ENAME CHECK(ENAME IS NOT NULL), CONSTRAINT EX_EMP5_FK_DEPTNO FOREIGN KEY(DEPTNO)REFERENCES EX_DEPT(DEPTNO)
);

-- 오라클은 테이블들을 관리하기 위한 테이블을 가지고 있음.
SELECT *
FROM USER_TABLES;

SELECT TABLE_NAME
FROM USER_TABLES;

-- 오라클은 제약조건들을 관리하기 위한 테이블을 가지고 있음.
SELECT *
FROM USER_CONSTRAINTS;