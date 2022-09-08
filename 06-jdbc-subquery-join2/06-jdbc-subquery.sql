
select * from item;
select id,name,maker,price from item where price = (select min(price) from item);

select * from jdbc_employee;
-- test.step4.TestFindMakerAndSumPriceListGroupByMaker
--  item table 의 maker 를 기준으로 그룹화하여 maker 와 maker별 상품 총액(SUM_PRICE)을 
-- 집계하여 조회 , 총액 내림차순으로 정렬 
-- 만약 총액이 2000 이상인 그룹
select maker, sum(price) "SUM_PRICE" , count(*) "item_count"
from item
group by maker
having sum(price) >= 2000
order by SUM_PRICE desc;