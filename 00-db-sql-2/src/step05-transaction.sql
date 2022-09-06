select * from emp;
select * from emp where ename  like '%S%';
select * from emp where ename like '%'||S||'%';

/*
  Transaction ( 트랜잭션 ) : 작업단위
  										더 이상 쪼갤 수 없는 작업 단위 -> 원자성
  										데이터 베이스의 상태를 변경시키기 위해 수행하는 작업단위
 TCL ( Transaction Controller Language )
 	-	COMMIT : 변경된 작업 내용을 실제 데이터베이스에 반영
 	-  ROLLBACK : 변경된 작업 내용을 취소하고 이전 상태로 되돌림
 	  
 	  사례 ) 카드를 발급하면 포인트 지급을 약속
 	  			카드발급 트랜잭션 => 1. 카드발급 + 2. 포인트 발급 
 	  			 1번 2번 두 사항이 모두 정상적으로 수행되었을 때 실제 작업내용이 DB에 저장되어야 한다 => commit
 	  			 만약 1번 2번의 세부 작업이 문제가 발생할 경우에는 , 예를 들면 카드는 발급되고 , 포인트 지급시
 	  			 문제가 발생될 경우에는 작업 내용을 취소하고 원 상태로 되돌려야 한다 => rollback
 	  		- Java Application 상에서 Transaction 처리
		try {
			// 자동커밋모드에서 수동커밋모드로 변경 -> 수동 커밋모드 : 직접 커밋을 명령하기 전까지는 실제 db에 반영되지 않음 
			connection.setAutoCommit(false);
			세부작업 1. 카드발급 
			세부가업 2. 포인트발급
				모든 세부작업이 정상적으로 실행되었을 때 실제 db에 반영하도록
				connection.commit(); 을 실행한다 	
		} catch(Exception e) {
			 // 문제발생시 넘어옴
			 connection.rollback(); // 작업취소하고 원상 복귀 시킨다 
		}		 	  			 
 */

