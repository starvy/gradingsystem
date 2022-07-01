create sequence hibernate_sequence start 1 increment 1;
create table Users (id int8 not null, email varchar(255), fullName varchar(255), password varchar(255), username varchar(255), primary key (id));
alter table if exists Users add constraint UK_ncoa9bfasrql0x4nhmh1plxxy unique (email);
alter table if exists Users add constraint UK_23y4gd49ajvbqgl3psjsvhff6 unique (username);
