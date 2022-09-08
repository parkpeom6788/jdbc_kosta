select id,name,maker,price from item;

-- 최저가 item 정보를 조회 sql을 subquery 로 이용해 조회 
select id,name,maker,price from item where price = (select min(price) from item);

-- 사원 테이블
create table jdbc_employee(
	empno number primary key,
	name varchar2(100) not null,
	job varchar2(100) not null,
	salary number not null
)
select * from jdbc_employee;

-- 시퀀스 생성
create sequence jdbc_employee_seq;

insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'아이유','개발',700);
insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'손석구','개발',800);
insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'강하늘','개발',900);
insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'유재석','총무',600);
insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'박보검','개발',900);
insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'이상순','총무',600);
insert into jdbc_employee(empno,name,job,salary) values(jdbc_employee_seq.nextval,'손흥민','총무',900);

select count(*) from jdbc_employee;

select * from jdbc_employee;

delete from jdbc_employee where name ='아이유';

delete from jdbc_employee;

-- job 의 종류를 조회
select distinct job from jdbc_employee;

commit 

-- JOB이 '개발'인 사원중 가장 높은 SALARY 를 받는 사원의 리스트
select max(salary) from jdbc_employee where job='개발';

-- TestFindEmployeeListByHighestSalaryAndJob
/*
 * 1. 개발 job의 최고 salary 를 조회
 * 2. 개발 job 사원 중 최고 salary 를 받는 사원를 조회
 */
select empno,name,job,salary
from jdbc_employee where job='개발'
and salary = (select max(salary)
from jdbc_employee where job='개발');
----------------------------------------------
-- test.step4.TestFindMakerAndSumPriceListGroupByMaker
--  item table 의 maker 를 기준으로 그룹화하여 maker 와 maker별 상품 총액(SUM_PRICE)을 
-- 집계하여 조회 , 총액 내림차순으로 정렬 
-- 만약 총액이 2000 이상인 그룹

select id,name,maker,price from item;

select maker , sum(price) as "SUM_PRICE" , count(*) as "item_count"
from item
group by maker
having sum(price) >= 2000
order by SUM_PRICE desc; 



