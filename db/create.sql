create sequence hibernate_sequence start 1 increment 1;
create table t_grades (id int8 not null, description varchar(255), title varchar(255) not null, value int2 not null, student_id int8, primary key (id));
create table t_subjects (id int8 not null, name varchar(255) not null, primary key (id));
create table t_users (id int8 not null, email varchar(255), fullName varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id));
alter table if exists t_users add constraint UK_1f8qpknpngd98342v0j2ceadc unique (email);
alter table if exists t_users add constraint UK_sp0e01od15gf4nu5ffu87qb9n unique (username);
alter table if exists t_grades add constraint FKjlfutrdmwjp6wfn7housavsk7 foreign key (student_id) references t_users;
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (1, 'debil@gmail.com', '', '$2a$05$Jrq2TDwLUIPFo5Yda8G8neJrd1DdtDjOJjnaRcI9aJ132QUGOLesK', 'USER', 'debil');
INSERT INTO public.t_users (id, email, fullname, password, role, username) VALUES (2, 'blatny@blatny.cz', 'Honza', '$2a$12$mlEnv1aMgHvafYCNOBG4N.KUYTS.SLFZtYfnXedUPmJfgeyIlsBKu', 'SUPERADMIN', 'superadmin');