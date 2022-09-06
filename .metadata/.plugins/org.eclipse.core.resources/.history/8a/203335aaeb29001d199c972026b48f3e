/*
	SQL ( Structured Query Language ) : 데이터베이스를 정의 , 조작 , 제어 하는 언어
	
	1. DDL ( data definition language ) : 데이터 정의어 - create , drop ,alter 테이블의 구조 변경
	2.DML ( data manipulation language ) : 데이터 조작어 - 
		insert , select ,update , delete 정보 조회 생성 수정 삭제
		( CRUD : Create , Read , Update , Delete )
	3.DCL ( Data Control Language : 데이터 제어어 ) - GRANT 자원에 접근할 권한을 준다
		grant connect , resource to scott - 권한 부여 GRANT , REVOKE 권한 취소
	4.TCL ( transaction control language ) - commit ( 메모리를 실제 디스크로 올림 )
		실제 DB반영 , ROLLBACK 작업 취소 원상태로 돌림 
		ROLLBACK 예 ) 한사람이 돈을 빼서 넣는 작업 돈을 뺏는데 문제 발생 시 취소
-- SQL 실행시에는 영역 선택 후 alt + x 또는 마.오.후 execute selected text 를 선택하면 된다
-- DDL
-- create : 테이블 생성
-- table : 데이터 저장하는 공간 , column : 속성 attribute 
data type : 자료형 , constraint : 제약 조건
소문자 우리가 지음 , 대문자 예약어 , 제약 조건
-- id : column 명
-- VARCHAR2(100) : 오라클 문자열 데이터 타입
-- PRIMARY KEY : constraint 제약 조건 PK - UNIQUE 유일 + NOT NULL : 유일해야 하며 데이터가 존재해야 함
*/
create table member2 (
	id varchar2(100) primary key,
	password varchar2(100) not null,
	name varchar2(100) not null,
	address varchar2(100)
)


