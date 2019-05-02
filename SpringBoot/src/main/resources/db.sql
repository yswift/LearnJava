CREATE DATABASE LearnJava DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

GRANT ALL privileges  ON LearnJava.* TO learnjavauser@'%' IDENTIFIED BY 'learnjavapwd';
-- 刷新，生效
FLUSH PRIVILEGES;

create table departments
(
	Id nvarchar(50) not null,
	Name nvarchar(50) null,
	constraint Departments_pk
		primary key (Id)
);

create table users
(
	Id nvarchar(50) not null,
	Name nvarchar(50) null,
	Password nvarchar(50) null,
	Department_Id nvarchar(50) not null,
	constraint Users_pk
		primary key (Id),
	constraint Users_Departments_Id_fk
		foreign key (Department_Id) references departments (Id)
			on update cascade on delete cascade
);

create table roles
(
	Id nvarchar(50) not null,
	Name nvarchar(50) null,
	constraint Roles_pk
		primary key (Id)
);

create table user_role
(
	Id int auto_increment,
	Role_Id nvarchar(50) not null,
	User_Id nvarchar(50) not null,
	constraint UserRole_pk
		primary key (Id),
	constraint UserRole_Roles_Id_fk
		foreign key (Role_Id) references roles (Id)
			on update cascade on delete cascade,
	constraint UserRole_Users_Id_fk
		foreign key (User_Id) references users (Id)
			on update cascade on delete cascade
);


insert into departments(Id, Name) values('01','人文学院');
insert into departments(Id, Name) values('02','理学院');
insert into departments(Id, Name) values('03','工学院');
insert into departments(Id, Name) values('04','商学院');
insert into departments(Id, Name) values('05','数学学院');
insert into departments(Id, Name) values('06','外国语学院');
insert into departments(Id, Name) values('07','体育学院');
insert into departments(Id, Name) values('08','美术学院');
insert into departments(Id, Name) values('09','音乐学院');
insert into departments(Id, Name) values('10','教师教育学院');
insert into departments(Id, Name) values('11','国际学院');
insert into departments(Id, Name) values('12','社会科学部');
insert into departments(Id, Name) values('13','生命科学与技术学院');
insert into departments(Id, Name) values('14','政治学与国际关系学院');
insert into departments(Id, Name) values('15','思想政治理论教研部');
insert into departments(Id, Name) values('16','红河州民族师范学校');
insert into departments(Id, Name) values('41','图书馆');
insert into departments(Id, Name) values('42','后勤管理处');
insert into departments(Id, Name) values('43','财务处');
insert into departments(Id, Name) values('44','信息技术中心');
insert into departments(Id, Name) values('61','学院办公室');
insert into departments(Id, Name) values('62','纪委办公室、监察审计处');
insert into departments(Id, Name) values('63','党委组织部');
insert into departments(Id, Name) values('64','党委宣传部');
insert into departments(Id, Name) values('65','人事处');
insert into departments(Id, Name) values('66','教务处');
insert into departments(Id, Name) values('67','学生工作部、学生处');
insert into departments(Id, Name) values('68','招生就业处');
insert into departments(Id, Name) values('69','科技处、学报编辑部');
insert into departments(Id, Name) values('70','对外交流与合作处');
insert into departments(Id, Name) values('71','资产管理处');
insert into departments(Id, Name) values('72','基建处');
insert into departments(Id, Name) values('73','武装部、保卫处');
insert into departments(Id, Name) values('74','远程与成人教育学院');
insert into departments(Id, Name) values('75','工会');
insert into departments(Id, Name) values('76','团委');
insert into departments(Id, Name) values('77','党校');
