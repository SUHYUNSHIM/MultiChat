delete from member where nickname is null;
create table member (nickname varchar(30), region varchar(30), birthday char(8));

select * from member;


drop table member;

select * from member where nickname = '����';
select nickname from member where substr(birthday,1,4) = (select substr(birthday,1,4) from member where nickname = '������')
and nickname <> '������';
select count(*) from all_tables where table_name = 'MEMBER';

select nickname from member where region =  (select region from member where nickname = '����') and nickname <> '����';

update member
set birthday = '20000423'
where region = '���۱�';

alter table member modify(nickname varchar(30));
alter table member modify(region varchar(30));

insert into member values ("���","���۱�","19880225");

select count(*) from all_tables where table_name = 'member';   
