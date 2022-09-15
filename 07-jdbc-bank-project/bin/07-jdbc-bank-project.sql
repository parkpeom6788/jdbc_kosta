-- DDL 
create table account (
	ACCOUNT_NUM NUMBER PRIMARY KEY,
	ACCOUNT_NAME VARCHAR2(100) NOT NULL,
	ACCOUNT_PASSWORD VARCHAR2(100) NOT NULL,
	ACCOUNT_TYPE VARCHAR2(100) CHECK(ACCOUNT_TYPE IN('여행','주택','자기개발'))  NOT NULL,
	ACCOUNT_BALANCE NUMBER NOT NULL
);  -- 계좌 개설 
CREATE SEQUENCE ACCOUNT_SEQ;

-- DML
select * from account;
drop table account;

-- 계좌 잔액 조회
select * from account where ACCOUNT_NUM = 1 AND  ACCOUNT_PASSWORD = 1234;

select ACCOUNT_NUM,ACCOUNT_NAME,ACCOUNT_PASSWORD,ACCOUNT_TYPE,ACCOUNT_BALANCE  from account where account_num=1;

select ACCOUNT_NUM,ACCOUNT_NAME,ACCOUNT_PASSWORD,ACCOUNT_TYPE , ACCOUNT_BALANCE from account
where account_balance = 
    (select max(account_balance) from account);

-- 계좌 비밀번호 체크
select account_num,account_name,account_password from account 
where account_num = (select account_num from account where account_num = '1' and account_password = '1234');

-- 계좌타입별로 계좌수 랑 평균잔액

select  ACCOUNT_TYPE , count(ACCOUNT_TYPE) 계좌수 , avg(ACCOUNT_BALANCE) 평균잔액  from account  group by ACCOUNT_TYPE order by  계좌수 desc;
