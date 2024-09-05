-- 사용자 시퀀스 생성
create sequence SEQ_TB_MEMBER;

-- 사용자 테이블 생성
CREATE TABLE "TB_MEMBER" 
   (	"M_NO" CHAR(6 BYTE), 
	"ID" VARCHAR2(20 BYTE), 
	"PW" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"GENDER" CHAR(1 BYTE), 
	"ADDR" VARCHAR2(100 BYTE), 
	"PHONE" VARCHAR2(20 BYTE), 
	"DELYN" CHAR(1 BYTE) DEFAULT ('N'), 
	"REGDATE" DATE DEFAULT SYSDATE, 
	 CHECK (GENDER IN ('F', 'M')) ENABLE, 
	 CHECK (DELYN IN ('Y','N')) ENABLE, 
	 PRIMARY KEY ("M_NO")
   );

create table tb_upload_file(
    f_no    number -- 기본키
    ,idx    number -- 기본키
    ,oname  varchar2(500)   
    ,sname  varchar2(500)-- 중복을 방지하기 위해서 중복되지 않는값을 생성해서 추가 - 파일명이 길어짐
    ,path   varchar2(100)
    ,file_type varchar2(100)
    ,regdate    date default sysdate-- 기본값 오늘날자및시간
    -- 여러개의 파이를 저장하기 위해서 idx 컬럼을 이용
    , PRIMARY KEY (f_no, idx)
);
-- 시퀀스 생성
create sequence seq_upload_file;

-- 업로드 파일테이블 데이터 입력
insert into tb_upload_file 
        (f_no, idx, oname, sname, path, file_type)
        values(1,1,'oname','sname', 'path', 'img'); 

-- 도서테이블에 첨부파일의 파일번호 컬럼을 추가
alter table tb_book add f_no number;

select * from tb_book;

-- 첨부파일 번호를 업데이트
update tb_book
set f_no=1
where b_no='B00111'; 

-- 첨부파일 목록을 조회
select *
from tb_book b, tb_upload_file f
where b.f_no = f.f_no(+); 