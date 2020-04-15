create table College
(
    Id varchar(10) not null,
    Name varchar(50) not null,
    constraint College_pk
        primary key (Id)
);

create table Student
(
    Id int auto_increment
        primary key,
    No varchar(50),
    Name varchar(50),
    Age int null,
    Birthday date null,
    Photo blob null
);

insert into Student values(null, '201187654321', '憨豆', 67, '1955-01-06', null);



