-- DDL 
CREATE TABLE account(
	account_no NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	password VARCHAR2(100) NOT NULL,
	account_type VARCHAR2(100) check( account_type IN('여행','주택','자기개발')) NOT NULL,
	balance NUMBER NOT NULL
)
CREATE SEQUENCE account_seq;
DROP SEQUENCE account_seq;
SELECT * FROM account;

-- DML 
COMMIT
-- 1. 계좌개설 


INSERT INTO account(account_no,name,password,account_type,balance) VALUES(account_seq.nextval,'아이유','1234','여행',1000);
INSERT INTO account(account_no,name,password,account_type,balance) VALUES(account_seq.nextval,'손석구','4321','자기개발',2000);
INSERT INTO account(account_no,name,password,account_type,balance) VALUES(account_seq.nextval,'강하늘','4321','자기개발',2000);
INSERT INTO account(account_no,name,password,account_type,balance) VALUES(account_seq.nextval,'장원영','4321','주택',2000);

DELETE FROM account;

SELECT * FROM account;


-- 2. 계좌 잔액조회 
-- 계좌가 없을 때 조회가 결과행이 없다 
SELECT password,balance FROM account WHERE account_no=11;
-- 계좌가 존재할 때에는 패스워드를 확인 
SELECT password,balance FROM account WHERE account_no=1;


-- 3. 계좌번호 유무와 비밀번호 일치 여부를 확인 
SELECT password FROM account WHERE account_no=11;
SELECT password FROM account WHERE account_no=1;

-- 4. 입금 ( 계좌번호 account_no 3을 이용해 sql test ) 
SELECT * FROM account WHERE account_no=3;
-- update 구문으로 account_no 3 의 계좌에 1000원을 입금해본다 
UPDATE account SET balance=balance+1000 WHERE account_no=3;
-- 5. 출금 
SELECT * FROM account WHERE account_no=3;
UPDATE account SET balance=balance-1000 WHERE account_no=3;

-- 6. 계좌번호에 따른 계좌존재여부 
-- 존재하지 않으면 0 
SELECT COUNT(*) FROM account WHERE account_no=11;
-- 존재하면 1 
SELECT COUNT(*) FROM account WHERE account_no=1;

-- 7. 계좌이체 
-- 출금sql 
UPDATE account SET balance=balance-1000 WHERE account_no=3;
-- 입금sql 
UPDATE account SET balance=balance+1000 WHERE account_no=3;

-- 8. 최고잔액을 가진 계좌를 조회 
-- 90000
SELECT MAX(balance) FROM account;
-- 위 sql을 subquery로 활용 
SELECT account_no,name,balance FROM account 
WHERE balance=(SELECT MAX(balance) FROM account) 


-- 9. 계좌타입별 계좌수와 평균잔액을 조회
SELECT account_type,COUNT(*), AVG(balance) FROM account
GROUP BY account_type
