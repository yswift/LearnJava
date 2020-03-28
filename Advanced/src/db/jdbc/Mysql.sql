create schema LearnJava collate utf8mb4_unicode_ci;

create table College
(
    Id char(2) charset utf8 not null
        primary key,
    Name varchar(50) charset utf8 not null
);

create table Student
(
    Id int auto_increment
        primary key,
    No varchar(50) charset utf8 null,
    Name varchar(50) charset utf8 null,
    Age int null,
    Birthday date null,
    Photo blob null
);

create table departments
(
    Id varchar(50) charset utf8 not null
        primary key,
    Name varchar(50) charset utf8 null
);

create table roles
(
    Id varchar(50) charset utf8 not null
        primary key,
    Name varchar(50) charset utf8 null
);

create table users
(
    Id varchar(50) charset utf8 not null
        primary key,
    Name varchar(50) charset utf8 null,
    Password varchar(50) charset utf8 null,
    Department_Id varchar(50) charset utf8 not null,
    constraint Users_Departments_Id_fk
        foreign key (Department_Id) references departments (Id)
            on update cascade on delete cascade
);

create table user_role
(
    Id int auto_increment
        primary key,
    Role_Id varchar(50) charset utf8 not null,
    User_Id varchar(50) charset utf8 not null,
    constraint UserRole_Roles_Id_fk
        foreign key (Role_Id) references roles (Id)
            on update cascade on delete cascade,
    constraint UserRole_Users_Id_fk
        foreign key (User_Id) references users (Id)
            on update cascade on delete cascade
);

