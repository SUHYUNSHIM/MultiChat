select * from member;
drop table member;

create table member (nickname varchar(30), region varchar(30), birthday char(8));

select * from member where nickname = '�ᰡ��';

select count(*) from all_tables where table_name = 'MEMBER';

update member
set birthday = '20000423'
where region = '���۱�';

alter table member modify(nickname varchar(30));
alter table member modify(region varchar(30));


select count(*) from all_tables where table_name = 'member';   
