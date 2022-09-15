select deptno,dname,loc,tel from k_department; -- 컬럼명을 적어주면 나중에 추후 개발시 도움이 됨
select empno,ename,sal,job,deptno from K_EMPLOYEE;

select e.empno,e.ename,e.sal,e.job,e.deptno,d.dname,d.loc,d.tel
from k_employee e
inner join k_department d
on e.deptno = d.deptno
where e.deptno = 10;

desc k_department;

select e.empno,e.ename,e.sal,e.job,e.deptno,d.deptno,d.dname,d.loc,d.tel
from K_EMPLOYEE e
inner join k_department d
on e.deptno = d.deptno
where empno = 1; 
