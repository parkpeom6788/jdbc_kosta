-- GUESTBOOK 생성
create table guestbook (
	guestbook_no number primary key,
	title varchar2(100) not null,
	content varchar2(100) not null
)
-- DDL : 테이블 삭제
drop table  guestbook;

-- 시퀀스 생성 : GUESTBOOK_NO PK 값을 자동 생성
create sequence guestbook_seq;

-- 시퀀스 삭제 
drop sequence guestbook_seq;

select * from guestbook;

-- 시퀀스 목록
select * from user_sequences;

-- 현재 시퀀스값 확인
select last_number 
from user_sequences
where sequence_name = 'GUESTBOOK_SEQ';

-- 시퀀스 초기화
-- 현재 시퀀스의 increment 를 현재 값 만큼 차감
alter sequence GUESTBOOK_SEQ increment by +1;

-- 시퀀스 다음 값 실행
select GUESTBOOK_SEQ.NEXTVAL from dual;

-- 현재 시퀀스값 조회
select GUESTBOOK_SEQ.CURRVAL from dual;

-- 시퀀스의 increment를 1로 설정
alter sequence GUESTBOOK_SEQ increment by 1;

select * from guestbook;

-- title 에 즐 문자열이 포함된 정보를 조회(where)
select guestbook_no,title,content from guestbook where title like '%즐%';

-- JDBC의 PreparedStatement 상에서 like 적용은 아래와 같이 한다
-- 참고 ) 문자열 합치기 || 
select '불'||'화' from dual;
select '불타는'||'화요일' from dual;

-- 아래는 dao 에서 적용할 sql 임 ( 여기서는 실행 안됨 ) 
-- 메모리에 올려둬서 검색 키워드 세팅해줘서 바로 사용 속도가 빠름
-- ? 엄격하게 체크해서 해킹 sql 인젝션:주입 나쁜 의도 sql 해킹등을 막아냄  
-- pstmt.setString()
select guestbook_no,title,content from guestbook where title like '%' || ? || '%';

 select guestbook_no,title,content from guestbook where title like '%'||'즐'||'%';













