delete from member where nickname is null;
create table member (nickname varchar(30), region varchar(30), birthday char(8));

select * from member;


drop table member;

select * from member where nickname = '감자';
select nickname from member where substr(birthday,1,4) = (select substr(birthday,1,4) from member where nickname = '오렌지')
and nickname <> '오렌지';
select count(*) from all_tables where table_name = 'MEMBER';

select nickname from member where region =  (select region from member where nickname = '고구마') and nickname <> '고구마';

update member
set birthday = '20000423'
where region = '동작구';

alter table member modify(nickname varchar(30));
alter table member modify(region varchar(30));

insert into member values ("당근","동작구","19880225");

select count(*) from all_tables where table_name = 'member';   
