create sequence hibernate_sequence start 1 increment 1;
create table t_grades (id int8 not null, description varchar(255), title varchar(255) not null, value int2 not null, studentId_id int8, subject_id int8, primary key (id));
create table t_subjects (id int8 not null, name varchar(255) not null, primary key (id));
create table t_users (id int8 not null, email varchar(255), fullName varchar(255), password varchar(255), username varchar(255), primary key (id));
alter table if exists t_users add constraint UK_1f8qpknpngd98342v0j2ceadc unique (email);
alter table if exists t_users add constraint UK_sp0e01od15gf4nu5ffu87qb9n unique (username);
alter table if exists t_grades add constraint FKaw1ucn0q4n12q00uh72qcg5ee foreign key (studentId_id) references t_users;
alter table if exists t_grades add constraint FKqgid0d6nv6xp9tgi2k4w05urp foreign key (subject_id) references t_subjects;
