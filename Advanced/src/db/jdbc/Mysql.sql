
-- college表
create table College
(
	Id nchar(2) not null,
	Name nvarchar(50) not null,
	constraint College_pk
		primary key (Id)
);
