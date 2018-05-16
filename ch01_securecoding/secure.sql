create user secure identified by 1111;

grant dba to secure;

create table users(
    id varchar2(30) primary key,
    password varchar2(30) not null,
    name varchar2(30) not null
);

select * from users;

create table photos(
    idx number primary key,
    description varchar2(1000),
    url varchar2(100) not null
);

create sequence seq_photos_idx;

create table comments(
    idx number primary key,
    u_id varchar2(30) references users(id),
    content varchar2(1000) not null
);
drop table comments;
create sequence seq_comments_idx;
select * from photos;
select * from comments; 
alter table users add login_count number(1);
select * from users;
update users set login_count = 0;
alter table users add login_fail_time timestamp;
delete from comments;
delete from photos;
